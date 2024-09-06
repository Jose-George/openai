```markdown
# OpenAI API
```
## Executar Projeto

Para iniciar o projeto, utilize o Docker Compose. Siga os passos abaixo:

```bash
cd parallax-api/docker
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




