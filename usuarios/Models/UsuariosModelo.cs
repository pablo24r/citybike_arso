using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;


namespace usuarios.Modelo
{
    public class Usuario
    {

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("Nombre")]
        public string Nombre{ get; set; } = string.Empty;

        [BsonElement("Email")]
        public string Email { get; set; } = string.Empty;

        [BsonElement("Nick")]
        public string Nick { get; set; } = string.Empty;

        [BsonElement("PasswordHash")]
        public string PasswordHash { get; set; } = string.Empty;

        [BsonElement("OAuth2Id")]
        public string OAuth2Id { get; set; } = string.Empty;

        [BsonElement("Role")]
        public string Role { get; set; } = "Usuario";
        
        [BsonElement("Activado")]
        public bool Activado { get; set; } = false;
        
    }
        

    public class ActivationCode
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("UserId")]
        public string UserId { get; set; } = string.Empty;

        [BsonElement("Code")]
        public string Code { get; set; } = string.Empty;

        [BsonElement("Expiration")]
        public DateTime Expiration { get; set; }
    }


}