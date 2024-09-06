```markdown

# OBS: É preciso criar uma CONTA na OPENAI e colocar na variável de ambiente OPENAI_KEY a chave da conta.

```
## Executar Projeto

Para iniciar o projeto, utilize o Docker Compose. Siga os passos abaixo:

```bash
cd openai-api/docker
docker-compose up
```

Isso iniciará o banco de dados necessário para o projeto.

## Usuários Padrões

Aqui estão os usuários padrão para testar a API:

- **Perfil Admin**
    - Email: `sup@openai.com`
    - Senha: `123`

- **Perfil Basic**
    - Email: `basic@openai.com`
    - Senha: `123`

## Rotas

### Login

Faça login usando a seguinte rota:

```
POST http://localhost:8080/login
```

Body:

```json
{
  "email": "basic@openai.com",
  "password": "123"
}
```

No token jwt existe um campo chamado status que indica que o usuario está com o pagmento ativo. 

### Refresh Token

Obtenha um novo token usando a rota de refresh token:

```
POST http://localhost:8080/refresh-token
```

Body:

```json
{
  "token": "eOtww"
}
```

### Users (Admin)

Esta rota permite que apenas usuários com perfil de admin visualizem os usuários, tal como ative e desative. Faz parte da central de administração. APENAS USUARIOS ATIVADOS podem logar na plataforma.

```
GET http://localhost:8080/admin/users?isActive=true&name=sup
```

```
PUT http://localhost:8080/admin/{userId}/active
```

```
DELETE http://localhost:8080/admin/{userId}/active
```

### Chat 

Após a autenticação do usuário, é suficiente invocar o seguinte endpoint de forma repetida para estabelecer uma conexão com o histórico de memória da Open AI.
```
POST http://localhost:8080/sophia/chat
```

Body:

```json
{
  "message": "qual é o nome do meu irmão?"
}
```

