using usuarios.Modelo;
using usuarios.Servicio;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace usuarios.Controllers
{
    [Route("api/usuarios")]
    [ApiController]
    public class ActividadesController : ControllerBase
    {
        private readonly IServicioUsuarios _servicio;

        public ActividadesController(IServicioUsuarios servicio)
        {
            _servicio = servicio;
        }

        [HttpGet]
        public ActionResult<List<UsuariosResumen>> Get() =>
            _servicio.GetUsuarios();

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
        
        
    }
}