FROM mcr.microsoft.com/dotnet/sdk:6.0
WORKDIR /app

COPY . .
RUN dotnet build

ENV ASPNETCORE_KESTREL__ENDPOINTS__HTTP__URL=http://+:5000

CMD ["dotnet", "run"]
EXPOSE 5000