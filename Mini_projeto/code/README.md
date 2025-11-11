# 🎓 Mini Projeto — Fachada / API Gateway Acadêmico

> Universidade de Fortaleza (UNIFOR)
> Disciplina: Projeto de Arquitetura de Sistemas
> Professor: Nathalino Pachêco
> Aluno: Diogo Gifoni - 2410398
> Data de Entrega: 18/11/2025

---

## 🧩 Objetivo

Desenvolver uma **aplicação monolítica** que funcione como **Fachada (API Gateway)** para três microsserviços acadêmicos:
- 👨‍🎓 **Discente** — informações sobre alunos
- 📘 **Disciplina** — oferta de disciplinas
- 📚 **Biblioteca** — acervo de livros

O sistema realiza **consultas reais** (via requisições HTTP aos endpoints simulados) e **simulações locais** (matrículas e reservas) sem persistência permanente — os dados vivem apenas durante a execução.

---

## ⚙️ Escopo Funcional

### 🔍 Funcionalidades de Consulta
- Consultar dados de discentes: `id, nome, curso, modalidade, status`.
- Listar disciplinas disponíveis: `id, curso, nome, vagas`.
- Listar livros: `id, título, autor, ano, status (disponível ou indisponível)`.

### ✏️ Funcionalidades de Simulação (locais)
- Simular matrícula e cancelamento de disciplinas.
- Simular reserva e cancelamento de livros.

> Todas as simulações são **temporárias** e **não alteram os microsserviços externos**.

---

## 🧱 Arquitetura do Sistema

O sistema segue o padrão **MVC (Model–View–Controller)**, promovendo baixo acoplamento e alta coesão entre camadas.

