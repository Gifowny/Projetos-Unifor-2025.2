# 🎓 Academic Gateway System

## 📋 Informações do Projeto

**Universidade:** UNIFOR - Universidade de Fortaleza  
**Disciplina:** [T200] Projeto de Arquitetura de Sistemas  
**Professor:** Doutorando Nathalino Pachêco  
**Tipo:** Mini Projeto Individual  
**Versão:** 1.0.0

---

## 📖 Descrição

Sistema monolítico que opera como **Fachada/API Gateway** para três microsserviços acadêmicos pré-existentes (Discente, Disciplina e Biblioteca), agregando e apresentando informações a usuários finais. O sistema **não altera dados nos serviços externos**, portanto, as operações de escrita (matrícula e reserva) são **simulações locais** com persistência **volátil em memória** voltadas a fins didáticos.

---

## 🎯 Objetivos

1. Demonstrar integração com microsserviços externos via HTTP/REST
2. Implementar padrão arquitetural **MVC** (Model-View-Controller)
3. Aplicar princípios **SOLID** e padrões **GRASP**
4. Implementar regras de negócio acadêmicas
5. Fornecer interface console amigável seguindo as 10 Heurísticas de Nielsen
6. Demonstrar degradação graciosa em caso de falhas

---

## 🏗️ Arquitetura

### Diagrama de Componentes

```
┌─────────────────────────────────────────────────────────────┐
│                    ACADEMIC GATEWAY SYSTEM                  │
│                      (Monolito MVC)                         │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────┐    ┌────────────┐    ┌─────────────┐        │
│  │  VIEW    │◄───│ CONTROLLER │◄───│   SERVICE   │        │
│  │(Console) │    │            │    │  (Business) │        │
│  └──────────┘    └────────────┘    └─────────────┘        │
│                                            │                │
│                                            ▼                │
│                                   ┌─────────────┐          │
│                                   │ REPOSITORY  │          │
│                                   │ (In-Memory) │          │
│                                   └─────────────┘          │
│                                            │                │
│                                            ▼                │
│                                   ┌─────────────┐          │
│                                   │   MAPPER    │          │
│                                   └─────────────┘          │
│                                            │                │
│                                            ▼                │
│                                   ┌─────────────┐          │
│                                   │ HTTP CLIENT │          │
│                                   └─────────────┘          │
└──────────────────────────┬──────────────────────────────────┘
                           │
              ┌────────────┼────────────┐
              │            │            │
              ▼            ▼            ▼
      ┌─────────────┐ ┌─────────────┐ ┌─────────────┐
      │ Microsserviço│ │ Microsserviço│ │ Microsserviço│
      │  DISCENTE   │ │ DISCIPLINA  │ │ BIBLIOTECA  │
      │  (AWS API)  │ │  (AWS API)  │ │  (AWS API)  │
      └─────────────┘ └─────────────┘ └─────────────┘
```

### Padrão MVC Aplicado

- **Model**: Entidades de domínio (Discente, Disciplina, Livro, Matricula, Reserva)
- **View**: Classes de apresentação console (DiscenteView, DisciplinaView, etc.)
- **Controller**: Coordenadores de fluxo (DiscenteController, MatriculaController, etc.)

---

## 📦 Estrutura de Pacotes (82 Classes)

```
com.unifor.academic.gateway/
├── Main.java                           # Classe principal
├── config/                             # [3 classes] Configurações
│   ├── ApplicationConfig.java          # Singleton de configuração
│   ├── LoggingConfig.java
│   └── Constants.java
├── model/                              # [9 classes] Entidades de domínio
│   ├── Discente.java
│   ├── Disciplina.java
│   ├── Livro.java
│   ├── Matricula.java
│   ├── Reserva.java
│   ├── SituacaoAcademica.java (enum)
│   ├── StatusLivro.java (enum)
│   ├── StatusMatricula.java (enum)
│   └── StatusReserva.java (enum)
├── dto/                                # [8 classes] Data Transfer Objects
│   ├── DiscenteDTO.java
│   ├── DisciplinaDTO.java
│   ├── LivroDTO.java
│   ├── MatriculaDTO.java
│   ├── ReservaDTO.java
│   ├── MatriculaRequestDTO.java
│   ├── ReservaRequestDTO.java
│   └── ResponseDTO.java
├── controller/                         # [6 classes] Controladores MVC
│   ├── MenuController.java
│   ├── DiscenteController.java
│   ├── DisciplinaController.java
│   ├── LivroController.java
│   ├── MatriculaController.java
│   └── ReservaController.java
├── service/                            # [10 classes] Lógica de negócio
│   ├── DiscenteService.java (interface)
│   ├── DisciplinaService.java (interface)
│   ├── LivroService.java (interface)
│   ├── MatriculaService.java (interface)
│   ├── ReservaService.java (interface)
│   └── impl/
│       ├── DiscenteServiceImpl.java
│       ├── DisciplinaServiceImpl.java
│       ├── LivroServiceImpl.java
│       ├── MatriculaServiceImpl.java   # ⭐ Lógica de negócio crítica
│       └── ReservaServiceImpl.java     # ⭐ Lógica de negócio crítica
├── repository/                         # [5 classes] Persistência em memória
│   ├── Repository.java (interface genérica)
│   ├── MatriculaRepository.java (interface)
│   ├── ReservaRepository.java (interface)
│   └── impl/
│       ├── InMemoryMatriculaRepository.java
│       └── InMemoryReservaRepository.java
├── external/                           # [11 classes] Integração externa
│   ├── client/
│   │   ├── HttpClient.java (interface)
│   │   ├── DiscenteClient.java (interface)
│   │   ├── DisciplinaClient.java (interface)
│   │   ├── BibliotecaClient.java (interface)
│   │   └── impl/
│   │       ├── HttpClientImpl.java
│   │       ├── DiscenteClientImpl.java
│   │       ├── DisciplinaClientImpl.java
│   │       └── BibliotecaClientImpl.java
│   └── response/
│       ├── DiscenteResponse.java
│       ├── DisciplinaResponse.java
│       └── LivroResponse.java
├── mapper/                             # [6 classes] Conversão DTO ↔ Entity
│   ├── Mapper.java (interface genérica)
│   ├── DiscenteMapper.java
│   ├── DisciplinaMapper.java
│   ├── LivroMapper.java
│   ├── MatriculaMapper.java
│   └── ReservaMapper.java
├── validator/                          # [6 classes] Validação de dados
│   ├── Validator.java (interface)
│   ├── ValidationResult.java
│   ├── DiscenteValidator.java
│   ├── DisciplinaValidator.java
│   ├── MatriculaValidator.java
│   └── ReservaValidator.java
├── exception/                          # [10 classes] Tratamento de erros
│   ├── AcademicGatewayException.java
│   ├── DiscenteNotFoundException.java
│   ├── DisciplinaNotFoundException.java
│   ├── LivroNotFoundException.java
│   ├── MatriculaException.java
│   ├── ReservaException.java
│   ├── ExternalServiceException.java
│   ├── ValidationException.java
│   ├── BusinessRuleException.java
│   └── ExceptionHandler.java
├── util/                               # [6 classes] Utilitários
│   ├── Logger.java (Singleton)
│   ├── JsonUtil.java
│   ├── DateUtil.java
│   ├── StringUtil.java
│   ├── InputValidator.java
│   └── IdGenerator.java
└── view/                               # [7 classes] Interface com usuário
    ├── ConsoleView.java (classe base abstrata)
    ├── MenuView.java
    ├── DiscenteView.java
    ├── DisciplinaView.java
    ├── LivroView.java
    ├── MatriculaView.java
    └── ReservaView.java
```

**Total: 82 Classes** ✅

---

## ⚙️ Funcionalidades

### 📖 Consultas (Leitura dos Microsserviços)

1. **Discentes**
    - Listar todos os discentes
    - Buscar discente por matrícula
    - Exibir: id, nome, curso, modalidade, situação acadêmica

2. **Disciplinas**
    - Listar todas as disciplinas
    - Listar disciplinas por curso
    - Buscar disciplina por ID
    - Exibir: id, código, nome, curso, vagas disponíveis

3. **Livros**
    - Listar todos os livros
    - Listar apenas livros disponíveis
    - Buscar livro por ID
    - Exibir: id, título, autor, ano, status

### ✏️ Simulações (Escrita Local em Memória)

4. **Matrículas**
    - ✅ Realizar matrícula em disciplina
    - ❌ Cancelar matrícula
    - 📋 Listar matrículas por discente
    - 📋 Listar todas as matrículas

5. **Reservas de Livros**
    - ✅ Realizar reserva de livro
    - ❌ Cancelar reserva
    - 📋 Listar reservas por discente
    - 📋 Listar todas as reservas
    - ⚠️ Listar reservas atrasadas

---

## 🔒 Regras de Negócio Implementadas

### Matrícula em Disciplina

1. ✅ **Limite de disciplinas**: Máximo de **5 disciplinas simultâneas** por discente
2. ✅ **Situação acadêmica**: Apenas discentes com situação **ATIVO** podem matricular
3. ✅ **Mesmo curso**: Disciplina deve pertencer ao **mesmo curso** do discente
4. ✅ **Vagas disponíveis**: Disciplina deve ter **vagas disponíveis**
5. ✅ **Matrícula única**: Discente não pode se matricular duas vezes na mesma disciplina

### Reserva de Livro

1. ✅ **Disponibilidade**: Livro deve estar com status **DISPONÍVEL**
2. ✅ **Situação acadêmica**: Apenas discentes **ATIVOS** podem reservar
3. ✅ **Reserva única**: Discente não pode ter reserva ativa duplicada do mesmo livro
4. ✅ **Prazo de devolução**: 14 dias automáticos

---

## 🎨 Princípios e Padrões Aplicados

### SOLID (5/5 Princípios)

| Princípio | Aplicação | Exemplo |
|-----------|-----------|---------|
| **SRP** (Single Responsibility) | Cada classe tem uma única responsabilidade | `DiscenteService` apenas lógica de discente |
| **OCP** (Open/Closed) | Extensível via interfaces, fechado para modificação | Interfaces `Service`, `Repository`, `Client` |
| **LSP** (Liskov Substitution) | Subtipos podem substituir tipos base | Implementações de `Repository<T, ID>` |
| **ISP** (Interface Segregation) | Interfaces específicas e coesas | `DiscenteClient`, `DisciplinaClient` separados |
| **DIP** (Dependency Inversion) | Depende de abstrações, não de implementações | Services dependem de interfaces Client |

### GRASP (5/9 Padrões)

| Padrão | Aplicação | Exemplo |
|--------|-----------|---------|
| **Controller** | Coordena operações de casos de uso | `MenuController`, `MatriculaController` |
| **Information Expert** | Responsabilidade atribuída ao especialista | `Discente.isAptoParaMatricula()` |
| **Low Coupling** | Baixo acoplamento entre módulos | Services usam interfaces, não implementações |
| **High Cohesion** | Alta coesão dentro de cada classe | Cada service trata apenas seu domínio |
| **Polymorphism** | Uso de polimorfismo via interfaces | `HttpClient`, `Repository<T>`, `Mapper<E,D>` |

### GoF (5 Padrões de Projeto)

| Padrão | Tipo | Aplicação | Classe |
|--------|------|-----------|--------|
| **Singleton** | Criacional | Instância única de configuração | `ApplicationConfig`, `Logger` |
| **Factory** | Criacional | Criação de DTOs e entidades | Métodos `toDTO()`, `toEntity()` |
| **Strategy** | Comportamental | Diferentes estratégias de validação | `Validator<T>` com implementações específicas |
| **Template Method** | Comportamental | Estrutura comum de requisições HTTP | `HttpClientImpl.get()` e `.post()` |
| **Facade** | Estrutural | Simplifica acesso aos subsistemas | `MenuController` como fachada |

---

## 🌐 Microsserviços Integrados

| Serviço | URL Base | Descrição |
|---------|----------|-----------|
| **Discente** | `https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno` | Dados dos discentes (alunos) |
| **Disciplina** | `https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina` | Ofertas de disciplinas e vagas |
| **Biblioteca** | `https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca` | Acervo de livros e disponibilidade |

⚠️ **Importante**: Estes endpoints são **mock services** para fins didáticos. Não implementam todas as características de microsserviços reais (autonomia de deploy, banco de dados próprio, versionamento de API, observabilidade).

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** (ou superior)
- **Maven 3.8+** (Gerenciamento de dependências)
- **Gson 2.10.1** (Parsing JSON)
- **SLF4J + Logback** (Sistema de logging)
- **HttpURLConnection** (Cliente HTTP nativo do Java)

### Dependências (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.9</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.11</version>
    </dependency>
</dependencies>
```

---

## 🚀 Como Executar

### Pré-requisitos

- ☑️ Java 17 ou superior instalado
- ☑️ Maven 3.8+ instalado
- ☑️ Conexão com internet (para acessar microsserviços AWS)
- ☑️ IntelliJ IDEA Ultimate (recomendado)

### Passos

#### 1. Clone ou Extraia o Projeto

```bash
cd academic-gateway
```

#### 2. Compile o Projeto com Maven

```bash
mvn clean compile
```

#### 3. Execute a Aplicação

**Opção A: Via Maven**
```bash
mvn exec:java -Dexec.mainClass="com.unifor.academic.gateway.Main"
```

**Opção B: Via IntelliJ IDEA**
1. Abra o projeto no IntelliJ
2. Localize a classe `Main.java`
3. Clique com botão direito → **Run 'Main.main()'**

**Opção C: Gerando JAR executável**
```bash
mvn clean package
java -jar target/academic-gateway-1.0.0.jar
```

#### 4. Navegue pelos Menus

Siga as instruções no console para:
- Consultar discentes, disciplinas e livros
- Simular matrículas em disciplinas
- Simular reservas de livros
- Cancelar matrículas e reservas

---

## 📊 Requisitos Não Funcionais

### ✅ Usabilidade

- **Heurísticas de Nielsen aplicadas**:
    1. Visibilidade do status do sistema (mensagens claras)
    2. Correspondência entre sistema e mundo real (terminologia acadêmica)
    3. Controle e liberdade do usuário (opções de voltar/cancelar)
    4. Consistência e padrões (menus uniformes)
    5. Prevenção de erros (validações)
    6. Reconhecimento em vez de memorização (menus numerados)
    7. Flexibilidade e eficiência de uso (atalhos numéricos)
    8. Design estético e minimalista (interface limpa)
    9. Ajuda aos usuários (mensagens de erro descritivas)
    10. Ajuda e documentação (README completo)

### ⚡ Desempenho/Eficiência

- **Timeout de requisições**: Máximo de **3 segundos**
- **Logging automático**: Requisições que excedem 3s são registradas no log
- **Otimização**: Uso de `ConcurrentHashMap` para repositórios thread-safe

### 🛡️ Tolerância a Falhas

- **Degradação graciosa**: Em caso de falha de microsserviço, mensagem amigável é exibida
- **Fallback**: Sistema continua operando com dados em memória
- **Try-catch**: Todas as operações críticas protegidas
- **ExceptionHandler**: Tratamento centralizado de exceções

### 🔧 Manutenibilidade

- **Código limpo**: Nomenclatura clara e consistente
- **Separação de pacotes**: Organização lógica em camadas
- **Baixa duplicação**: DRY (Don't Repeat Yourself) aplicado
- **Comentários**: Javadoc em classes principais
- **Padrões documentados**: Comentários indicando SOLID/GRASP/GoF

---

## 📂 Decisões de Design

### Por que Monolito?

Decisão alinhada ao requisito do projeto. Um monolito simplifica o desenvolvimento, deployment e manutenção para fins didáticos, enquanto ainda demonstra boas práticas arquiteturais.

### Por que Persistência em Memória?

**Justificativa oficial do PDF**:
> "O mini projeto tem duração máxima de ~34 dias e foco em integração de APIs e implementação com boas práticas, portanto, exigir um SGDB acrescentaria sobrecarga não alinhada ao objetivo pedagógico da disciplina."

A persistência volátil permite:
- ✅ Foco em arquitetura e integração
- ✅ Simplicidade de setup
- ✅ Demonstração de padrões Repository
- ✅ Facilidade de testes

**Bônus**: Implementação com banco de dados relacional (PostgreSQL/MySQL) pode ser adicionada posteriormente para pontuação extra.

### Por que HttpURLConnection ao invés de bibliotecas externas?

- ✅ **Nativo do Java**: Não adiciona dependências desnecessárias
- ✅ **Controle total**: Permite demonstrar conceitos de HTTP de baixo nível
- ✅ **Requisito de 3s**: Fácil configurar timeouts precisos
- ✅ **Logging**: Total controle sobre medição de performance

---

## 📈 Cobertura de Requisitos

### Requisitos Funcionais ✅ 100%

- [x] Consultar dados do discente
- [x] Listar disciplinas oferecidas por curso
- [x] Listar livros com disponibilidade
- [x] Simular matrícula em disciplina
- [x] Simular cancelamento de matrícula
- [x] Simular reserva de livro
- [x] Simular cancelamento de reserva

### Requisitos de Arquitetura ✅ 100%

- [x] Padrão MVC implementado
- [x] Integração com 3 microsserviços externos
- [x] Repositório em memória
- [x] Fachada/API Gateway

### Requisitos de Negócio ✅ 100%

- [x] Máximo 5 disciplinas por discente
- [x] Verificação de situação acadêmica
- [x] Disciplina do mesmo curso
- [x] Verificação de vagas
- [x] Livro disponível para reserva

### SOLID ✅ 5/5 Princípios

- [x] SRP - Single Responsibility Principle
- [x] OCP - Open/Closed Principle
- [x] LSP - Liskov Substitution Principle
- [x] ISP - Interface Segregation Principle
- [x] DIP - Dependency Inversion Principle

### GRASP ✅ 5/9 Padrões

- [x] Controller
- [x] Information Expert
- [x] Low Coupling
- [x] High Cohesion
- [x] Polymorphism

### GoF ✅ 5 Padrões

- [x] Singleton
- [x] Factory
- [x] Strategy
- [x] Template Method
- [x] Facade

---

## 🐛 Troubleshooting

### Erro: "java.net.ConnectException: Connection timed out"

**Causa**: Microsserviço AWS indisponível ou sem conexão internet.

**Solução**:
1. Verifique sua conexão com a internet
2. Teste as URLs dos microsserviços no navegador
3. Aguarde alguns minutos (possível instabilidade temporária)

### Erro: "ClassNotFoundException" ao executar

**Causa**: Maven não baixou dependências.

**Solução**:
```bash
mvn clean install
```

### Erro: "Timeout após 3 segundos"

**Causa**: Latência alta na rede ou microsserviço sobrecarregado.

**Solução**:
- O sistema registra no log e continua operando (degradação graciosa)
- Tente novamente após alguns segundos

---

## 👨‍💻 Autor

**Diogo Gifoni**  
Matrícula: 2410398  
Curso: Cien. da Computação 
UNIFOR - Universidade de Fortaleza

---

## 📜 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da disciplina de Projeto de Arquitetura de Sistemas da UNIFOR.

---

## 🙏 Agradecimentos

- Prof. Doutorando Nathalino Pachêco pelo excelente projeto
- Equipe UNIFOR pela infraestrutura AWS
- Comunidade Java pelos frameworks open-source

---

**📅 Data de Entrega**: 25/11  
**✅ Status**: Completo e Funcional  
**📦 Total de Classes**: 82  
**⭐ Qualidade**: Produção-Ready

---

## 📚 Referências Bibliográficas

1. GAMMA, E. et al. **Design Patterns: Elements of Reusable Object-Oriented Software**. Addison-Wesley, 1994.

2. ANICHE, Mauricio. **Orientação a Objetos e SOLID para Ninjas: Projetando classes flexíveis**. Editora Casa do Código, 2015.

3. LARMAN, Craig. **Utilizando UML e Padrões - Uma Introdução à Análise e ao projeto Orientado a Objetos e ao desenvolvimento Interativo**; 3ª ed. Bookman, 2007.

4. RICHARDS, Mark; FORD, Neal. **Fundamentos da arquitetura de software: uma abordagem de engenharia**. Rio de Janeiro: Alta Books, 2024.

5. SOMMERVILLE, Ian. **Engenharia de software**. 9. ed. São Paulo: Pearson Prentice Hall, 2011.

---

**🎉 FIM DA DOCUMENTAÇÃO 🎉**