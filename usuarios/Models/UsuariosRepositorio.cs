using Repositorio;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using MongoDB.Bson;
using usuarios.Modelo;

namespace usuarios.Repositorio
{


    public class RepositorioUsuariosMongoDB : Repositorio<Usuario, string>
    {
        private readonly IMongoCollection<Usuario> usuarios;
        string uri = "mongodb+srv://pabloraullopezmartinez:ARSO2024@clusterarso.w0erjqo.mongodb.net/?retryWrites=true&w=majority&appName=ClusterARSO";
        public RepositorioUsuariosMongoDB()
        {
            var client = new MongoClient(uri);
            var database = client.GetDatabase("ClusterARSO");

            usuarios = database.GetCollection<Usuario>("usuarios.net");
        }

        public string Add(Usuario entity)
        {
            usuarios.InsertOne(entity);

            return entity.Id;
        }

        public void Update(Usuario entity)
        {
            usuarios.ReplaceOne(usuario => usuario.Id == entity.Id, entity);
        }

        public void Delete(Usuario entity)
        {
            usuarios.DeleteOne(usuario => usuario.Id == entity.Id);
        }

        public List<Usuario> GetAll()
        {
            return usuarios.Find(_ => true).ToList();
        }

        public Usuario GetById(string id)
        {
            return usuarios
                .Find(usuario => usuario.Id == id)
                .FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas =  usuarios.Find(_ => true).ToList();

            return todas.Select(p => p.Id).ToList();

        }
    }

    public class RepositorioCodigosMongoDB : Repositorio<ActivationCode, string>
    {
        private readonly IMongoCollection<ActivationCode> codigos;
        string uri = "mongodb+srv://pabloraullopezmartinez:ARSO2024@clusterarso.w0erjqo.mongodb.net/?retryWrites=true&w=majority&appName=ClusterARSO";
        public RepositorioCodigosMongoDB()
        {
            var client = new MongoClient(uri);
            var database = client.GetDatabase("ClusterARSO");

            codigos = database.GetCollection<ActivationCode>("codigos.net");
        }

        public string Add(ActivationCode entity)
        {
            codigos.InsertOne(entity);

            return entity.Id;
        }

        public void Update(ActivationCode entity)
        {
            codigos.ReplaceOne(usuario => usuario.Id == entity.Id, entity);
        }

        public void Delete(ActivationCode entity)
        {
            codigos.DeleteOne(codigo => codigo.Id == entity.Id);
        }

        public List<ActivationCode> GetAll()
        {
            return codigos.Find(_ => true).ToList();
        }

        public ActivationCode GetById(string id)
        {
            return codigos
                .Find(codigo => codigo.Id == id)
                .FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas =  codigos.Find(_ => true).ToList();

            return todas.Select(p => p.Id).ToList();

        }
    }


}