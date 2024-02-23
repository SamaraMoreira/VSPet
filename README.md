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
| situação | string |     ✅     | Situação do funcionário em nosso sistema (Ativo/Inativo)
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

**Schema** 
```js
{
  "id": 1,
  "nomeFunc": "Vinicius Monteiro",
  "cpf": "52883339990",
  "situação": "ativa",
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
| Observações | string |     ✅     | Campo para observações sobre o pet.
