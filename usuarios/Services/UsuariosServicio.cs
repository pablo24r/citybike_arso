using usuarios.Modelo;
using Repositorio;
using System.Security.Cryptography;
using System.Text;


namespace usuarios.Servicio
{

    public class UsuariosResumen
    {

        public String Id { get; set; } = string.Empty;
        public String Nombre { get; set; } = string.Empty;
        public String Nick { get; set; } = string.Empty;
        public String NickGitHub { get; set; } = string.Empty;
        public String Role { get; set; } = string.Empty;
        public bool Activado { get; set;}
    }
    public interface IServicioUsuarios
    {
        ActivationCode SolicitudCodigoActivacion(string idUsuario);

        Usuario? AltaUsuario(string codigo, string nombre, string nick, string password);

        Usuario? AltaUsuario(string codigo, string nombre, string nickGitHub);

        void BajaUsuario(string idUsuario);

        Dictionary<string,string> VerificarCredenciales(string nick, string contraseña);

        Dictionary<string,string> VerificarOAuth2(string nick);

        List<UsuariosResumen> GetListadoUsuarios();
    }

    public class ServicioUsuarios : IServicioUsuarios
    {

        private Repositorio<Usuario, String> RepoUsuarios;
        private Repositorio<ActivationCode, String> RepoCodigos;

        public ServicioUsuarios(Repositorio<Usuario, String> usuarios, Repositorio<ActivationCode, String> codigos)
        {

            this.RepoUsuarios = usuarios;
            this.RepoCodigos = codigos;
        }

        public ActivationCode SolicitudCodigoActivacion(string idUsuario)
        {
            var code = new ActivationCode
        {
            UserId = idUsuario,
            Code = GenerateActivationCode(),
            Expiration = DateTime.UtcNow.AddHours(24)
        };
        RepoCodigos.Add(code);
        return code;
        }

        private string GenerateActivationCode()
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            var random = new Random();
            var code = new char[6];
            for (int i = 0; i < code.Length; i++)
            {
                code[i] = chars[random.Next(chars.Length)];
            }
            return new string(code);
        }

        private static string HashPassword(string password)
        {
            var sha256 = SHA256.Create();
            var bytes = Encoding.UTF8.GetBytes(password);
            var hash = sha256.ComputeHash(bytes);
            return Convert.ToBase64String(hash);
        }



       public Usuario? AltaUsuario(string codigo, string nombre, string nick, string password)
        {
            var Code = RepoCodigos.GetAll().Find(c => c.Code == codigo);
            if (Code != null && Code.Expiration > DateTime.Now)
        {
            var usuario = RepoUsuarios.GetById(Code.UserId);
            if (usuario != null)
            {
                usuario.Activado = true; // Lo doy de alta
                usuario.Nick = nick;
                usuario.PasswordHash = HashPassword(password);
                RepoCodigos.Delete(Code); // Una vez usado, elimino el código del repositorio
                RepoUsuarios.Update(usuario);
                return usuario;
            }
        }
        return null;
        }


        public Usuario? AltaUsuario(string codigo, string nombre, string GitHubId)
        {
            var Code = RepoCodigos.GetAll().Find(c => c.Code == codigo);
            if (Code != null)
            {
                var usuario = RepoUsuarios.GetById(Code.UserId);
                if (usuario != null)
                {
                    usuario.Activado = true; // Lo doy de alta
                    usuario.Nombre = nombre;
                    usuario.OAuth2Id = GitHubId;                
                    RepoCodigos.Delete(Code); // Una vez usado, elimino el código del repositorio
                    RepoUsuarios.Update(usuario);
                    return usuario;
                }
            }
            return null;
        }


        public void BajaUsuario(string idUsuario)
        {
            var usuario = RepoUsuarios.GetById(idUsuario);
            if (usuario != null)
            {
                usuario.Activado = false;
                RepoUsuarios.Update(usuario);
            }
        }

        public Dictionary<string,string> VerificarCredenciales(string nick, string contraseña)
        {
            var usuario = RepoUsuarios.GetAll().Find(user => user.Nick == nick);
            if( usuario != null)
            {
                if (usuario.PasswordHash == HashPassword(contraseña))
                {
                    return new Dictionary<string, string>
                    {
                        { "id", usuario.Id },
                        { "nombre", usuario.Nombre },
                        { "nick", usuario.Nick },
                        { "passwordHash", usuario.PasswordHash },
                        { "rol", usuario.Role },
                        { "activado", usuario.Activado.ToString() }
                    };
                }
            }
            return new Dictionary<string, string>{};
        }

    public Dictionary<string,string> VerificarOAuth2(string nick)
        {
            var usuario = RepoUsuarios.GetAll().Find(user => user.OAuth2Id == nick);
            if( usuario != null)
            {
                {
                    return new Dictionary<string, string>
                    {
                        { "id", usuario.Id },
                        { "nombre", usuario.Nombre },
                        { "gitHub-Nick", usuario.OAuth2Id },
                        { "rol", usuario.Role },
                        { "activado", usuario.Activado.ToString() }
                    };
                }
            }
            return new Dictionary<string, string>{};
        }

        public List<UsuariosResumen> GetListadoUsuarios()
        {

            var resultado = new List<UsuariosResumen>();

            foreach (String id in RepoUsuarios.GetIds())
            {

                    var usuario = RepoUsuarios.GetById(id);
                    var resumen = new UsuariosResumen
                    {
                        Id = usuario.Id,
                        Nombre = usuario.Nombre,
                        Nick = usuario.Nick,
                        NickGitHub = usuario.OAuth2Id,
                        Role = usuario.Role
                    };
                    resultado.Add(resumen);
            }

            return resultado;
        }
    }
}