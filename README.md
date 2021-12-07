
# Microserviço Julgador

O microserviço julgador do Calango é uma aplicação Java Spring que recebe as submissões de algoritmos feitos 
na pseudolinguagem Calango em conjunto com os casos de teste
vindos da aplicação Web para realizar um julgamento e retornar um resultado. 

Se desejar testar apenas como um serviço isolado e fazer as requisições diretamente, apenas siga os
passos abaixo. Caso contrário, também siga as instruções do 
[repositório da aplicação web](https://github.com/GeovanaRamos/calango-online-judge) 
para ter a solução completa em funcionamento.

## Arquitetura e Tecnologias

Para o funcionamento completo da solução, são necessários os seguintes serviços:

**Serviço** | **Linguagem** | **Framework** | **Repositório**
---|---|---------------|---
Plataforma Web | Python | Django        | [GitHub](https://github.com/GeovanaRamos/calango-online-judge)
Microserviço Julgador | Java | Spring        | Aqui

Outros repositórios importantes para este projeto:

**Serviço** | **Linguagem** | **Framework** | **Repositório**
---|---|---------------|---
Calango IDE | Java | Swing   | [GitHub](https://github.com/GeovanaRamos/calango)
Calango Interpretador | Java | *Puro*        | [GitHub](https://github.com/GeovanaRamos/calango-interpreter)


## Rodando Localmente


Primeiramente, clone este repositório:

```
git clone https://github.com/GeovanaRamos/calango-judge-service.git
```

O repositório já possui duas formas pré-configuradas de ser executado,
com Docker ou com Maven. Para rodar com Maven, utilize uma IDE compatível 
para facilmente executar a aplicação. Abaixo, seguem as instruções de como
executar com Docker.

Crie a imagem docker partir do Dockerfile neste repositório. Observe que é necessário ter as credenciais do GitHub, 
pois o pacote do interpretador está hospedado no GitHub packages, e é uma
dependência desse projeto.

```
docker build -t cojservice --build-arg REPOSITORY_URL=https://maven.pkg.github.com/GeovanaRamos/calango-interpreter --build-arg SERVER_USERNAME=(USER) --build-arg SERVER_PASSWORD=(TOKEN) .

```

Agora execute a imagem expondo a porta para sua máquina:

```
docker run -it -p 8080:8080 cojservice 
```

Pronto. Agora você já pode fazer requisições para a rota _localhost:8080/judge_. O JSON abaixo
mostra um exemplo de como vocẽ deve fazer a requisição:

```json
{
	"code": "algoritmo converteParaMaiusculo;\n\nprincipal\n\tcaracter letra;\n\n\tleiaCaracter(letra);\n\n\tescreval(maiusculoCaracter(letra));\n\nfimPrincipal\n",
	"cases": [
		{
			"input": ["a"],
			"output": "A\n"
		},
		{
			"input": ["b"],
			"output": "B\n"
		},
		{
			"input": ["c"],
			"output": "C\n"
		}
	]
	
}
```

O qual retornaria a seguinte resposta:
```json
{
  "code": 1,
  "message": "ACCEPTED",
  "errorMessage": "No error message"
}
```

Essa imagem docker docker já está preparada para uso em produção, caso necessário.
