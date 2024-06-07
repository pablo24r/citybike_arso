using System;
using usuarios.Modelo;
using Repositorio;
using usuarios.Servicio;
using System.Linq;
using usuarios.Repositorio;
using System.Text.Json;
/*
namespace Pruebas
{
    class Program
    {
        static void Main(string[] args)
        {
            // Crear el repositorio 
            Repositorio<Usuario, String> usuarios = new RepositorioUsuariosMongoDB();
            Repositorio<ActivationCode, String> codigos = new RepositorioCodigosMongoDB();

            // Crear el servicio de usuarios
            var servicioUsuarios = new ServicioUsuarios(usuarios, codigos);

            // Crear un usuario de prueba
            var usuario = new Usuario
            {
                Nombre = "Pablo prueba2",
                Email = "pablo@example.com",
            };

            //string id = usuarios.Add(usuario);

            // Solicitar un código de activación para el usuario
            var activationCode = servicioUsuarios.SolicitudCodigoActivacion("6662e58c49097e0cb29e2461");

            servicioUsuarios.AltaUsuario(activationCode.Code, 1, "pablo24_r", "contraseña123");

            foreach (var user in servicioUsuarios.GetUsuarios()){
                Console.WriteLine(user.Id);
                Console.WriteLine(user.Nombre);
                Console.WriteLine(user.Role);
                Console.WriteLine(user.Email);
                Console.WriteLine();
            }

            Console.WriteLine(JsonSerializer.Serialize(servicioUsuarios.VerificarCredenciales("pablo24_r", "contraseña123")));


            // Verificar que el código se ha añadido correctamente
            //var usuarioConCodigo = repositorio.GetById(usuario.Id);
            //Console.WriteLine($"User's Activation Code: {usuarioConCodigo.Codigo.Code}");
            //Console.WriteLine($"User's Activation Code Expiration: {usuarioConCodigo.Codigo.Expiration}");
        }
    }
}
*/