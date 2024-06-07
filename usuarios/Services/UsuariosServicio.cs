using System;
using System.Collections.Generic;
using usuarios.Modelo;
using Repositorio;
using System.Security.Cryptography;

namespace usuarios.Servicio
{

    public class UsuariosResumen
    {

        public String Id { get; set; } = string.Empty;
        public String Nombre { get; set; } = string.Empty;
        public String Email { get; set; } = string.Empty;
        public String Role { get; set; } = string.Empty;


    }
    public interface IServicioUsuarios
    {
        List<UsuariosResumen> GetResumenes();

        ActivationCode SolicitudCodigoActivacion(string idUsuario);
    }

    public class ServicioUsuarios : IServicioUsuarios
    {

        private Repositorio<Usuario, String> repositorio;

        public ServicioUsuarios(Repositorio<Usuario, String> repositorio)
        {

            this.repositorio = repositorio;
        }
        

        public List<UsuariosResumen> GetResumenes()
        {

            var resultado = new List<UsuariosResumen>();

            foreach (String id in repositorio.GetIds())
            {

                    var usuario = repositorio.GetById(id);
                    var resumen = new UsuariosResumen
                    {
                        Id = usuario.Id,
                        Nombre = usuario.Nombre,
                        Email = usuario.Email,
                        Role = usuario.Role
                    };
                    resultado.Add(resumen);
            }

            return resultado;
        }

        public ActivationCode SolicitudCodigoActivacion(string idUsuario)
        {
            var code = new ActivationCode
        {
            UserId = idUsuario,
            Code = GenerateActivationCode(),
            Expiration = DateTime.UtcNow.AddHours(24)
        };
        return code;
        }

        private string GenerateActivationCode()
        {
            using var rng = RandomNumberGenerator.Create();
            var bytes = new byte[8];
            rng.GetBytes(bytes);
            return Convert.ToBase64String(bytes);
        }


    }

    


}