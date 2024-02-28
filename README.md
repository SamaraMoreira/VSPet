# VSPet 
Software de gerenciamento de petshop. 

## Requisitos

- [ ] CRUD Funcionário
- [ ] CRUD Pet
- [ ] CRUD Petshop
- [ ] CRUD Agendamento
- [ ] Listagem de serviços diários 
- [ ] Listagem de serviços gerais 
- [ ] Dashboard 
- [ ] Autenticação
- [ ] Meus dados

## Endpoints

### Funcionário

`GET` /funcionario

Lista todos os funcionários cadastrados no sistema.

**Códigos de status**

`200` sucesso

---

`GET` / funcionario /{id}

Retorna os detalhes de um funcionário com o `id` informado.

**Códigos de status**

`200` Sucesso

`404` Id não encontrado

---

`POST` /funcionário

Cadastrar um novo funcionário.

| campo | tipo | obrigatório | descrição 
|-------|------|:-------------:|----------
| nome | string|    ✅    | Nome completo do funcionário
| cpf  | string |     ✅     | Número do CPF do funcionário
| telefone  | string |     ✅     | Número do telefone do funcionário.
| cep | string |     ✅     | Número do cep referente ao endereço do funcionário.
| estado  | string |     ✅     | Sigla do estado que o funcionário reside.
| rua | string |     ✅     | Logradouro referente ao endereço do funcionário.
| cidade  | string |     ✅     | Nome da cidade  que o funcionário reside.
| complemento | string |     ✅     | Informações adicionais referente ao endereço do funcionário.
| numero  | string |     ✅     | Número da residência do funcionário.



 

**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

---
`PUT` /funcionario/{id}

Altera o funcionário com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| id | number |    ❌    | Número da matrícula do funcionário
| nomeFunc | string|    ✅    | Nome completo do funcionário
| cpf  | string |     ❌     | Número do CPF do funcionário
| telefone  | string |     ✅     | Número do telefone do funcionário.
| cep | string |     ✅     | Número do cep referente ao endereço do funcionário.
| estado  | string |     ✅     | Sigla do estado que o funcionário reside.
| rua | string |     ✅     | Logradouro referente ao endereço do funcionário.
| cidade  | string |     ✅     | Nome da cidade  que o funcionário reside.
| complemento | string |     ✅     | Informações adicionais referente ao endereço do funcionário.
| numero  | string |     ✅     | Número da residência do funcionário.

**Códigos de status**

`200` Sucesso

`404` id não encontrado

`400` Validação falhou

---

`DELETE` /funcionario/{id}

Realiza uma exclusão lógica, onde o funcionário com o `id` informado receberá a situação : INATIVO

**Códigos de status**

`204` Apagado com sucesso

`404` id não encontrado


**Schema** 
```js
{
  "id": 1,
  "nomeFunc": "Vinicius Monteiro",
  "cpf": "52883339990",
  "situação": "ATIVA",
  "telefone": "11964546800",
  "endereço": {
    "cep": "04317245",
    "rua": "rua",
    "cidade": "cidade",
    "complemento": "complemento",
    "numero": "numero"
  }
}


```

### Pet

`GET` /pet

Lista todos os pets cadastrados no sistema.

**Códigos de status**

`200` sucesso

---

`GET` / pet /{id}

Retorna os detalhes de um pet com o `id` informado.

**Códigos de status**

`200` Sucesso

`404` Id não encontrado

---

`POST` / pet

Cadastrar um novo pet.

| campo | tipo | obrigatório | descrição 
|-------|------|:-------------:|----------
| nomePet | string|    ✅    | Nome do pet
| nomeResp | string|    ✅    | Nome completo do responsável pelo pet.
| cpf  | string |     ✅     | Número do CPF do responsável pelo pet
| telefone  | string |     ✅     | Número do telefone do responsável pelo pet.
| raça | string |     ✅     | Raça do pet.
| nascimento  | date  |     ✅     | Data de nascimento do pet. 
| peso |  float  |     ✅     | Peso em kg do pet.
| Observações | string |     ❌     | Campo para observações sobre o pet.


`PUT` /pet/{id}

Altera o pet com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| idPet | long  |    ❌    | Número da matrícula do pet
| nomePet | string|    ✅    | Nome do pet
| nomeResp | string|    ✅    | Nome completo do responsável pelo pet.
| cpf  | string |     ✅     | Número do CPF do responsável pelo pet
| telefone  | string |     ✅     | Número do telefone do responsável pelo pet.
| raça | string |     ❌       | Raça do pet.
| nascimento  | date  |     ❌     | Data de nascimento do pet. 
| peso |  float  |     ✅     | Peso em kg do pet.

**Códigos de status**

`200` Sucesso

`404` id não encontrado

`400` Validação falhou

---

**Schema** 
```js
{
  "id": 1,
  "nomePet": "Low",
  "nomeResp": "Samara Moreira",
  "cpf": "0541254786",
  "telefone": "11964546800",
  "raça": "não informada",
  "nascimento": "05/08/2020",
  "peso": 8.5
}

```

### Agendamento

`GET`/agendamento

Este endpoint lista todos os agendamentos cadastrados no sistema com o status AGENDADO. Você pode filtrar os resultados por data e CPF.

Parâmetros de Consulta:

    data (opcional): Filtra os agendamentos pela data. Use o formato YYYY-MM-DD.
    cpf (opcional): Filtra os agendamentos pelo CPF do cliente.
    
Exemplo:

`GET` /agendamento?data=2024-02-28&cpf=12345678901

**Códigos de status**

`200` sucesso

---

`GET` / agendamento /{id}

Retorna os detalhes de um agendamento com o id informado.

**Códigos de status**

`200` Sucesso

`404` Id não encontrado

---

`POST` /agendamento

Cadastrar um novo agendamento, o status do agendamento será AGENDADO

| campo | tipo | obrigatório | descrição 
|-------|------|:-------------:|----------
| idPet | long  |    ✅   | Número da matrícula do pet
| idFunc | long |    ✅      | Número da matrícula do funcionário
| serviço | ENUM | ✅  | Tipo de serviço a ser realizado 
| data | date|    ✅    | Data e hora que será realizado o agendamento


**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

---

`DELETE` /agendamento/{id}

Realiza uma exclusão lógica, onde o agendamento com o `id` informado receberá o status : CANCELADO

**Códigos de status**

`204` Apagado com sucesso

`404` id não encontrado

---

`PUT` /agendamento/{id}

Altera o agendamento com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| idPet | long  |    ❌    | Número da matrícula do pet
| idFunc | long |    ✅      | Número da matrícula do funcionário
| serviço | ENUM | ✅  | Tipo de serviço a ser realizado 
| data | date|    ✅    | Data e hora que será realizado o agendamento

**Códigos de status**

`200` Sucesso

`404` id não encontrado

`400` Validação falhou

---

**Schema** 
```js
{
  "idPet": 1,
  "idFunc": 1,
  "serviço": "BANHO",
  "data": "2024-04-23T14:25:43Z"
  "status": AGENDADO
}


```


