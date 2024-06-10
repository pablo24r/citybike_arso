using usuarios.Modelo;
using usuarios.Servicio;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace usuarios.Controllers
{
    [Route("api/usuarios")]
    [ApiController]
    public class UsuariosController : ControllerBase
    {
        private readonly IServicioUsuarios _servicio;

        public UsuariosController(IServicioUsuarios servicio)
        {
            _servicio = servicio;
        }

        [HttpGet]
        public ActionResult<List<UsuariosResumen>> Get() =>
            _servicio.GetListadoUsuarios();


        [HttpGet("codigo/{idUsuario}")]
        public ActionResult<ActivationCode> SolicitudCodigoActivacion(string idUsuario)
        {
            var code = _servicio.SolicitudCodigoActivacion(idUsuario);
            return Ok(code);
        }

        [HttpPost("activar/{codigo}")]
        public ActionResult<ActivationCode> AltaUsuario(string codigo, [FromQuery] string nombre, [FromQuery] string nick, [FromQuery] string? password = null)
        {
            Usuario? usuario;
            if (!string.IsNullOrEmpty(password))
            {
                usuario = _servicio.AltaUsuario(codigo, nombre, nick, password);
            }
            else
            {
                usuario = _servicio.AltaUsuario(codigo, nombre, nick);
            }

            if (usuario != null)
            {
                return Ok(usuario);
            }
            else
            {
                return NotFound();
            }
        }
        
        [HttpPost("baja/{idUsuario}")]
        public IActionResult BajaUsuario(string idUsuario)
        {
            _servicio.BajaUsuario(idUsuario);
            return Ok();
        }
     	
     	[HttpPost("login")]
        public ActionResult<string> VerificarCredenciales([FromForm] string nick, [FromForm] string password)
        {
            var claims = _servicio.VerificarCredenciales(nick, password);
            if (claims.Count == 0)
            {
                return Unauthorized();
            }
            return Ok(claims);
        }

        [HttpPost("oauth2")]
        public ActionResult<string> VerificarOauth([FromForm] string nick)
        {
            var claims = _servicio.VerificarOAuth2(nick);
            if (claims.Count == 0)
            {
                return Unauthorized();
            }
            return Ok(claims);
        }   
        
    }

}