# VSPet 
Software de gerenciamento de petshop. 

## Requisitos

- [ ] CRUD Funcionário
- [ ] CRUD Pet
- [ ] CRUD Petshop
- [ ] CRUD Serviços 

## Endpoints

### Funcionário

`GET` /funcionario

Lista todos os funcionários ativos cadastrados no sistema.

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
| idPetShop | Long |   ✅    | id do petshop que o funcionário pertence
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

`404` Pet Shop não encontrado

---
`PUT` /funcionario/{id}

Altera o funcionário com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| idPetShop | Long |  ❌    | id do petshop que o funcionário pertence
| id | Long |    ❌    | Número da matrícula do funcionário
| nomeFunc | string|    ✅    | Nome completo do funcionário
| cpf  | string |     ❌     | Número do CPF do funcionário
| situacao  | ENUM |     ✅     | Situação do Funcionário , ATIVO ou INATIVO
| telefone  | string |     ✅     | Número do telefone do funcionário.
| cep | string |     ✅     | Número do cep referente ao endereço do funcionário.
| estado  | string |     ✅     | Sigla do estado que o funcionário reside.
| rua | string |     ✅     | Logradouro referente ao endereço do funcionário.
| cidade  | string |     ✅     | Nome da cidade  que o funcionário reside.
| complemento | string |     ✅     | Informações adicionais referente ao endereço do funcionário.
| numero  | string |     ✅     | Número da residência do funcionário.

**Códigos de status**

`200` Sucesso

`404` funcionário ou pet shop não encontrado 

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
  "id_petShop": 1
  "id": 1,
  "nomeFunc": "Vinicius Monteiro",
  "cpf": "52883339990",
  "situação": "ATIVO",
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
| idPetShop | Long |  ❌    | id do petshop que o funcionário pertence
| nomePet | string|    ✅    | Nome do pet
| nomeResp | string|    ✅    | Nome completo do responsável pelo pet.
| cpf  | string |     ✅     | Número do CPF do responsável pelo pet
| telefone  | string |     ✅     | Número do telefone do responsável pelo pet.
| raça | string |     ✅     | Raça do pet.
| nascimento  | date  |     ✅     | Data de nascimento do pet. 
| peso |  float  |     ✅     | Peso em kg do pet.
| Observações | string |     ❌     | Campo para observações sobre o pet.

**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

---

`PUT` /pet/{id}

Altera o pet com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| idPetShop | Long |  ❌    | id do petshop que o funcionário pertence
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
  "id_petShop": 1,
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

### Serviço

`GET`/servico/agendados

Este endpoint lista todos os serviços cadastrados no sistema com o status AGENDADO. Você pode filtrar os resultados por data do serviço e CPF.

Parâmetros de Consulta:

    data (opcional): Filtra os agendamentos pela data. Use o formato YYYY-MM-DD.
    cpf (opcional): Filtra os agendamentos pelo CPF do cliente.
    
Exemplo:

`GET` /servico/agendados?data=2024-02-28&cpf=12345678901

**Códigos de status**

`200` sucesso

---

`GET`/servico

Este endpoint lista todos os serviços cadastrados no sistema.

**Códigos de status**

`200` sucesso

---


`GET` /servico/{id}

Retorna os detalhes de um serviço com o id informado.

**Códigos de status**

`200` Sucesso

`404` Id não encontrado

---

`POST` /servico/agendar

Agendar um novo servico, o status do serviço será AGENDADO

| campo | tipo | obrigatório | descrição 
|-------|------|:-------------:|----------
| idPetShop | Long |  ❌    | id do petshop que o serviço pertence
| idPet | long  |    ✅   | Número da matrícula do pet
| idFunc | long |    ✅      | Número da matrícula do funcionário
| tipo_servico | ENUM | ✅  | Tipo de serviço a ser realizado 
| data_hora_servico | LocalDateTime |    ✅    | Data e hora que será realizado o serviço


**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

`404` pet shop não encontrado

---

`POST` /servico

Realiza um novo servico, o status do serviço será CONCLUÍDO, esse endpoint tem como função principal realizar serviços que não precisaram de um agendamento , neste caso havia horário disponível no momento

| campo | tipo | obrigatório | descrição 
|-------|------|:-------------:|----------
| idPetShop | Long |  ❌    | id do petshop que o serviço pertence
| idPet | long  |    ✅   | Número da matrícula do pet
| idFunc | long |    ✅      | Número da matrícula do funcionário
| tipo_servico | ENUM | ✅  | Tipo de serviço a ser realizado 
| data_hora_servico | LocalDateTime |    ✅    | Data e hora que será realizado o serviço


**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

`404` pet shop não encontrado

---

`DELETE` /servico/{id}

Realiza uma exclusão lógica, onde o servico com o `id` informado receberá o status : CANCELADO

**Códigos de status**

`204` Apagado com sucesso

`404` serviço não encontrado

---

`PUT` /servico/{id}

Altera o servico com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| idPetShop | Long |  ❌    | id do petshop que o serviço pertence
| idPet | long  |    ❌    | Número da matrícula do pet
| idFunc | long |    ✅      | Número da matrícula do funcionário
| status | ENUM |    ✅      | status do serviço , CONCUÍDO, AGENDADO
| tipo_servico | ENUM | ✅  | Tipo de serviço a ser realizado 
| data_hora_servico | LocalDateTime |    ✅    | Data e hora que será realizado o serviço
| data_hora_conclusao | LocalDateTime |   ❌   | Data e hora que o serviço foi concluído 

**Códigos de status**

`200` Sucesso

`404` serviço ou funcionário não encontrado

`400` Validação falhou

---

**Schema** 
```js
{
  "idPet": 1,
  "idFunc": 1,
  "serviço": "BANHO",
  "data_hora_servico": "2024-04-23T14:25:43Z",
  "data_hora_conclusao": "2024-04-23T14:25:43Z"
  "status": AGENDADO
}


```

### Pet Shop


`GET` /petshop/{id}

Retorna os detalhes de um petshop com o id informado.

**Códigos de status**

`200` Sucesso

`404` Id não encontrado

---


`POST` /petshop

Cadastra um novo petshop

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| usename | string |  ✅    | username para login na plataforma
| password | string |  ✅    | password para login na plataforma
| nome | string|    ✅    | Razão Social do Pet Shop
| cnpj  | string |     ✅     | Número do CNPJ do Pet Shop
| telefone  | string |     ✅     | Número do telefone para contato.
| cep | string |     ✅     | Número do cep referente ao endereço
| estado  | string |     ✅     | Sigla do estado que Petshop está localizado.
| rua | string |     ✅     | Logradouro referente ao endereço do Petshop
| cidade  | string |     ✅     | Nome da cidade  que o Petshop está localizado.
| complemento | string |     ✅  | Informações adicionais referente ao endereço que Petshop está localizado.
| numero  | string |     ✅     | Número do edifício..


**Códigos de status**

`201` Criado com sucesso

`400` Validação falhou

---

`DELETE` /servico/{id}

Realiza a exclusão de um petshop

**Códigos de status**

`204` Apagado com sucesso

`404` petshop não encontrado

---

`PUT` /petshop/{id}

Altera o petshop com o `id` informado.

| campo | tipo | alterável | descrição 
|-------|------|:-------------:|----------
| usename | string |  ✅    | username para login na plataforma
| password | string |  ✅    | password para login na plataforma
| nome | string|    ✅    | Razão Social do Pet Shop
| cnpj  | string |     ❌     | Número do CNPJ do Pet Shop
| telefone  | string |     ✅     | Número do telefone para contato.
| cep | string |     ✅     | Número do cep referente ao endereço
| estado  | string |     ✅     | Sigla do estado que Petshop está localizado.
| rua | string |     ✅     | Logradouro referente ao endereço do Petshop
| cidade  | string |     ✅     | Nome da cidade  que o Petshop está localizado.
| complemento | string |     ✅  | Informações adicionais referente ao endereço que Petshop está localizado.
| numero  | string |     ✅     | Número do edifício.

**Códigos de status**

`200` Sucesso

`404` petshop não encontrado

`400` Validação falhou

---

**Schema** 
```js
{
  "nome": "Pet Shop do Vini",
  "cnpj": "848128384912344"
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



