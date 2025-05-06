# ✈️ SlyScanner - Monitoramento e Análise de Passagens Aéreas

O **SlyScanner** é uma aplicação moderna para monitoramento, análise e notificação de passagens aéreas com melhores preços. O sistema é composto por um backend em Java com Spring Boot (suporte a OAuth2, JWT e Redis) e um frontend em React, focado em performance, usabilidade e integração com APIs externas.

---

## 📦 Tecnologias Utilizadas

### Backend
- Java 17+
- Spring Boot
- Spring Security + OAuth2
- Redis (armazenamento de tokens)
- PostgreSQL (banco de dados)
- JWT (autenticação e autorização)
- Swagger (documentação de API)

### Frontend
- React 18+
- React Router
- Axios
- TailwindCSS / Material UI
- JWT Auth

---

## 🔒 Autenticação

- Suporte a **OAuth2** com login via Google.
- Geração de token **JWT** por sessão.
- Tokens armazenados temporariamente no **Redis**, com expiração automática.

---

## 📚 Endpoints e Funcionalidades

### 🧑‍💼 Usuários
- Registro: `POST /auth/register`
- Login OAuth: `GET /oauth2/authorize`
- Perfil: `GET /usuarios/me`
- Atualização: `PUT /usuarios/me`
- Deleção (lógica): `DELETE /usuarios/me`

### 🛫 Voos
- Listagem: `GET /voos`
- Detalhes: `GET /voos/:id`
- [Admin] Cadastro: `POST /admin/voos`
- [Admin] Edição: `PUT /admin/voos/:id`
- [Admin] Remoção: `DELETE /admin/voos/:id`

### 📢 Alertas de Preço
- Criar alerta: `POST /alertas`
- Listar alertas: `GET /alertas`
- Remover alerta: `DELETE /alertas/:id`

### 💳 Pagamentos e Passagens
- Comprar passagem: `POST /passagens/comprar`
- Cancelar: `POST /passagens/:id/cancelar`
- Status de pagamento: `GET /pagamentos/:id/status`

---

## 🧱 Estrutura de Branches

- `main`: Branch principal, somente código estável e testado.
- `dev-[nome]`: Branches de desenvolvimento individuais, ex:
  - `dev-joao`
  - `dev-luana`

> **Observação:** todos os PRs devem partir de `dev-[nome]` para `main` e serão revisados por pelo menos outro membro da equipe.

---

## 🧪 Testes

- Backend: JUnit + Mockito
- Frontend: Jest + React Testing Library

Execute:
```bash
# Backend
./mvnw test

# Frontend
npm test
