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
            _servicio.GetUsuarios();


        [HttpPost("{idUsuario}")]
        public ActionResult<ActivationCode> SolicitudCodigoActivacion(string idUsuario)
        {
            var code = _servicio.SolicitudCodigoActivacion(idUsuario);
            return Ok(code);
        }

        [HttpPost("activar/{codigo}")]
        public ActionResult<ActivationCode> AltaUsuario(string codigo, [FromForm] string nick, [FromForm] string password)
        {
            if (_servicio.AltaUsuario(codigo, nick, password) != null)
                return Ok();
            else 
                return NotFound();
        }

        [HttpPost("recuperar/{codigo}")]
        public ActionResult<ActivationCode> RecuperarUsuario(string codigo, [FromForm] string nick, [FromForm] string GitHubId)
        {
            if( _servicio.RecuperarUsuario(codigo, nick, GitHubId) != null)
                return Ok();
            else
                return NotFound();
        }
        
        [HttpPut("{idUsuario}")]
        public IActionResult BajaUsuario(string idUsuario)
        {
            _servicio.BajaUsuario(idUsuario);
            return Ok();
        }
        
        
    }

    [Route("auth")]
    [ApiController]
    public class AuthController : ControllerBase
    {
         private readonly IServicioUsuarios _servicio;

        public AuthController(IServicioUsuarios servicio)
        {
            _servicio = servicio;
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

        [HttpPost("OAuth2")]
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