# MyIBPI — Aplicativo do Colégio IBPI

Aplicativo Android institucional do Colégio IBPI para a comunidade escolar: alunos, responsáveis, professores, secretaria, coordenação e setor financeiro.

---

## 1. Visão Geral

O MyIBPI centraliza a comunicação e o acompanhamento escolar do Colégio IBPI em um único aplicativo Android. Famílias consultam frequência, boletim, comunicados, situação financeira e documentos institucionais; a equipe escolar registra frequência, ocorrências, avisos e cobranças diretamente pelo app.

O aplicativo é organizado em **cinco abas principais**:

| # | Aba | Conteúdo | Escopo dos dados |
|---|---|---|---|
| 1 | Frequência e Ocorrências | Presença/faltas e ocorrências disciplinares e acadêmicas, com filtro por tipo | Sempre individual por aluno |
| 2 | Boletim | Notas e desempenho escolar | Sempre individual por aluno |
| 3 | Avisos | Comunicados e notificações | Individual (aluno), individual (responsável), por turma, por segmento ou todos |
| 4 | Financeiro | Mensalidades, boletos, PIX e comprovantes | Individual por aluno — **visível apenas para responsáveis** |
| 5 | Informações Úteis | Cards com links para documentos institucionais | Varia por card (ver seção 5.5) |

O app é a **fonte de verdade** dos dados acadêmicos — não existe sistema legado a integrar. Toda a informação nasce e vive no Firebase.

**Segmentos atendidos:** Ensino Fundamental, Ensino Médio, EJA e Cursos Livres.

---

## 2. Objetivos

- Dar às famílias acesso imediato e autônomo à vida escolar do aluno, reduzindo a demanda de atendimento da secretaria.
- Substituir comunicação dispersa (bilhete, WhatsApp, e-mail) por um canal institucional único e rastreável.
- Permitir que o professor registre frequência e ocorrências no momento em que acontecem, sem papel intermediário.
- Tornar a situação financeira transparente para o responsável, com acesso a boleto e PIX sem precisar ligar para o colégio.
- Consolidar documentos institucionais (calendários, critérios, proposta pedagógica) em um ponto de consulta sempre atualizado.

---

## 3. Usuários e Perfis de Acesso

Seis perfis, com visibilidade e permissão de escrita distintas.

### 3.1 Matriz de acesso

| Perfil | Frequência | Ocorrências | Boletim | Avisos | Financeiro | Informações Úteis |
|---|---|---|---|---|---|---|
| **Aluno** | Lê (própria) | ❌ Sem acesso | Lê (próprio) | Lê (destinados a ele) | ❌ Sem acesso | Lê (conforme turma/segmento) |
| **Responsável** | Lê (dos filhos) | Lê (dos filhos) | Lê (dos filhos) | Lê (destinados a ele e aos filhos) | Lê (dos filhos) | Lê (conforme turma/segmento dos filhos) |
| **Professor** | Lê e **lança** | Lê e **lança** | Lê | Lê | ❌ Sem acesso | Lê |
| **Secretaria** | Lê | Lê | Lê | Lê e **publica** | Lê | Lê e **gerencia** |
| **Coordenação** | Lê | Lê | Lê | Lê e **publica** | Lê | Lê e **gerencia** |
| **Financeiro** | ❌ Sem acesso | ❌ Sem acesso | ❌ Sem acesso | Lê | Lê e **lança** | Lê |

### 3.2 Regras de escopo

- **Professor:** enxerga exclusivamente os alunos das **turmas e disciplinas que leciona**. Não tem acesso a alunos de outros professores.
- **Responsável:** pode ter **vários filhos matriculados**. A interface possui um **seletor de aluno** que troca o contexto de todas as abas simultaneamente.
- **Aluno:** a aba 1 exibe **apenas frequência** — ocorrências disciplinares e acadêmicas ficam restritas ao responsável e à equipe escolar.
- **Financeiro:** a aba é ocultada por completo para o perfil aluno, não apenas esvaziada.

> ⚠️ A definir: um aluno pode ter mais de um responsável vinculado (pai *e* mãe com acessos independentes)? O modelo será construído como relação muitos-para-muitos para não bloquear essa evolução, mas a regra de negócio precisa ser confirmada.

### 3.3 Criação de contas

As contas são **criadas e importadas em lote pela secretaria**. Não existe autocadastro público: o app não oferece tela de "criar conta". Isso protege os dados de menores e garante que todo acesso corresponda a uma matrícula válida.

---

## 4. Plataforma e Tecnologias

### 4.1 Plataforma

- **Android nativo** (foco atual).
- **iOS previsto para o futuro** — a modelagem de dados e as regras de negócio ficam no Firebase/domínio, de modo que um app iOS futuro reaproveite o backend sem reescrita.
- **Celular em orientação retrato** é o único formato no escopo inicial.
- Distribuição pela **Play Store pública**, com login restrito a quem possui matrícula.

### 4.2 Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Arquitetura | MVVM com Clean Architecture pragmática |
| Assincronismo | Coroutines + Flow / StateFlow |
| Injeção de dependência | Hilt |
| Navegação | Navigation Compose |
| Autenticação | Firebase Authentication (e-mail + senha) |
| Banco de dados | Cloud Firestore |
| Arquivos | Firebase Storage (boletos, documentos, logo) |
| Testes | JUnit, Turbine, MockK, Compose UI Test |

### 4.3 Configuração do projeto

- `applicationId` / `namespace`: `com.colegioibpi.myibpi`
- `minSdk`: 24 · `targetSdk`/`compileSdk`: 35
- JVM target: 17 (JDK 17)
- Gradle com Kotlin DSL e **version catalog** (`gradle/libs.versions.toml`)

| Ferramenta | Versão |
|---|---|
| Android Gradle Plugin | 8.6.0 |
| Gradle | 8.7 |
| Kotlin | 2.0.21 |
| Android Studio | Ladybug 2024.2.1 |

#### Teto de versões imposto pela IDE

O **Android Studio Ladybug 2024.2.1 suporta AGP até a versão 8.6.0** — ele recusa o sync acima disso. Esse é o fator que limita toda a toolchain, e as versões acima foram escolhidas para caber nesse teto:

- `compileSdk`/`targetSdk` ficam em **35**, que é o máximo recomendado para o AGP 8.6.
- O Gradle fica em **8.7**, versão mínima exigida pelo AGP 8.6.
- `androidx.core:core-ktx` fixado em **1.15.0**; a 1.17.0 exige compileSdk 36 e a 1.19.0 exige AGP 9.1 + compileSdk 37.
- `material` em **1.12.0** e `appcompat` em **1.7.0**, pelo mesmo motivo.

> ⚠️ **Não atualizar essas dependências sem antes atualizar o Android Studio.** O `gradlew` compila com versões mais novas, mas o sync da IDE quebra. Ao migrar para um Studio 2025.x, é possível subir para AGP 8.13.x, Gradle 8.14.x, Kotlin 2.4.x e compileSdk 36 de uma vez.

O Kotlin 2.0 ainda aceita `kotlinOptions { jvmTarget = "17" }`, mas o projeto usa o DSL atual, que continuará válido nas versões futuras:

```kotlin
kotlin {
    compilerOptions { jvmTarget = JvmTarget.JVM_17 }
}
```

> **Substituição em relação ao template atual:** o projeto existente foi gerado com o template de *Views* (`appcompat` + `material`, Java 8, compileSdk 34). A FASE 1 substitui essa base por Compose, eleva a compileSdk e o JVM target, e introduz a modularização.

### 4.4 Modularização

| Módulo | Responsabilidade | Situação |
|---|---|---|
| `app` | Activity, Application, grafo de navegação e barra de abas | ✅ criado |
| `core:designsystem` | Tema IBPI: cores, tipografia, base do Material 3 | ✅ criado |
| `core:ui` | Componentes de tela reutilizáveis e estados compartilhados | ✅ criado |
| `core:navigation` | As cinco abas principais e suas rotas | ✅ criado |
| `feature:attendance` | Frequência e Ocorrências (aba 1) | ✅ criado (placeholder) |
| `feature:report` | Boletim (aba 2) | ✅ criado (placeholder) |
| `feature:announcements` | Avisos (aba 3) | ✅ criado (placeholder) |
| `feature:finance` | Financeiro (aba 4) | ✅ criado (placeholder) |
| `feature:info` | Informações Úteis (aba 5) | ✅ criado (placeholder) |
| `core:common` | Utilitários, `Result`, extensões, formatação de data | ⏳ quando houver uso real |
| `core:firebase` | Clientes de Auth, Firestore e Storage | ⏳ depende do projeto Firebase |
| `core:testing` | Regras de teste, dispatchers e fakes compartilhados | ⏳ junto com os primeiros ViewModels |
| `feature:auth` | Login, recuperação de senha, sessão, seletor de aluno | ⏳ FASE 2 |

Cada `feature` segue a estrutura `presentation` / `domain` / `data` conforme ganha conteúdo real — hoje as features têm apenas `presentation/screen`, já que o conteúdo ainda é um placeholder. Não há módulo `core:database` porque o app não tem requisito offline: o cache do próprio Firestore atende à navegação entre telas.

Os módulos ainda **não usam convention plugins**. Cada `build.gradle.kts` declara sua própria configuração Android, o que gera alguma repetição mas mantém tudo explícito e legível. Quando o número de módulos crescer (ou a configuração ficar mais elaborada), vale extrair um `build-logic`; a task está registrada no `TASKS.md`.

### 4.5 Navegação

A enum `MainDestination` (em `core:navigation`) é a **única fonte de verdade** das abas: define rota, rótulo, ícone e ordem. Tanto a barra inferior quanto o `NavHost` são construídos a partir dela, então incluir, remover ou reordenar uma aba acontece em um único arquivo.

A troca de abas não empilha destinos — volta ao início do grafo preservando o estado de cada aba (`saveState`/`restoreState`), de forma que alternar entre abas não cria histórico crescente nem recarrega telas já visitadas.

> ⚠️ A visibilidade por perfil **ainda não é aplicada**: hoje as cinco abas aparecem para qualquer usuário. A aba Financeiro deve ficar oculta para o perfil aluno, o que será implementado junto com a autenticação (FASE 2).

---

## 5. Funcionalidades Principais

### 5.1 Autenticação e sessão

- Login por **e-mail e senha** (Firebase Authentication).
- Recuperação de senha por e-mail.
- Sessão persistente: o usuário permanece conectado entre aberturas do app.
- Roteamento inicial por perfil — cada perfil vê um conjunto diferente de abas.
- **Seletor de aluno** no topo, para responsáveis com mais de um filho.
- Sem autocadastro; sem login social; sem biometria ou PIN local.

### 5.2 Frequência e Ocorrências

**Consulta** (responsável, aluno — só frequência, professor, secretaria, coordenação):
- Lista cronológica de registros do aluno.
- **Filtro por tipo de item:** frequência · ocorrências disciplinares · ocorrências acadêmicas.
- Detalhe do registro: data, disciplina/turma, descrição, autor do lançamento.

**Lançamento** (professor):
- Seleção de turma/disciplina entre as que leciona.
- Registro de presença/falta da turma em uma data.
- Registro de ocorrência disciplinar ou acadêmica para um aluno específico.

### 5.3 Boletim

- Consulta das notas e do desempenho do aluno, individual.
- Organização por período letivo e por disciplina.

> ⚠️ A definir: o **sistema de avaliação** do colégio. Períodos são bimestrais, trimestrais ou semestrais? Existem quantas avaliações por período? Como se calcula a média e qual a nota de aprovação? Há recuperação? A estrutura difere entre EF, EM, EJA e Cursos Livres? **Esta definição é pré-requisito para a modelagem do Boletim** e precisa ser respondida antes da task correspondente na FASE 3.

### 5.4 Avisos

**Consulta:** lista de comunicados destinados ao usuário, com data, título, corpo e autor.

**Publicação** (secretaria e coordenação), com seleção de destinatário:

| Alvo | Descrição |
|---|---|
| Aluno individual | Um aluno específico |
| Responsável individual | Um responsável específico |
| Turma | Todos os alunos e responsáveis de uma turma |
| Segmento | Ensino Fundamental · Ensino Médio · EJA · Cursos Livres |
| Todos | Toda a comunidade escolar |

### 5.5 Financeiro

Visível **exclusivamente para responsáveis**, por aluno.

- **Extrato de mensalidades** com valor, vencimento e situação (em aberto · pago · vencido).
- **2ª via de boleto** em PDF, com linha digitável/código de barras copiável.
- **PIX** no formato copia-e-cola e QR Code.
- **Histórico de pagamentos** com data de quitação e comprovante para download.

**Lançamento** (setor financeiro): cadastro das cobranças, upload do PDF do boleto, informação do código PIX e baixa de pagamento.

> Não há gateway de pagamento integrado. Boletos e códigos PIX são **produzidos fora do app** e lançados manualmente pelo setor financeiro. O modelo de dados é desenhado para comportar uma integração automática (Asaas, Pagar.me, banco) numa fase futura sem migração.

### 5.6 Informações Úteis

Cards com link para conteúdo institucional. Cada card tem um escopo de visibilidade próprio:

| Card | Escopo |
|---|---|
| Horário das aulas | Por turma |
| Calendário de avaliação | Por segmento |
| Calendário escolar | Único para todos |
| Critérios de avaliação | Por segmento |
| Proposta Pedagógica | Único para todos |
| Informações sobre Dependências | Por segmento |
| Informações sobre Eletivas | Por segmento |
| Informações sobre Tutoria | Por aluno |

Secretaria e coordenação gerenciam o conteúdo e os arquivos desses cards.

### 5.7 Fora do escopo inicial

- **Notificações push (FCM)** — ⚠️ A definir. A arquitetura fica preparada (token de dispositivo e segmentação de público já contemplados no modelo de avisos), mas nenhuma notificação será implementada até haver decisão.
- **Funcionamento offline** — o app exige conexão e exibe estado de erro quando não houver internet. Sem Room, sem sincronização de lançamentos offline.
- **Layout para tablet** e **modo escuro**.
- **Painel web administrativo** — ver seção 9.3.

---

## 6. Requisitos de Segurança

### 6.1 Dados tratados

O app processa **dados pessoais de menores de idade** (identificação, frequência, desempenho acadêmico, ocorrências disciplinares) e **dados financeiros** das famílias. É o tipo de base que exige controle de acesso rigoroso.

### 6.2 Controles implementados

- Autenticação obrigatória em todas as telas; nenhum dado acessível sem sessão.
- **Regras de segurança do Firestore** espelhando a matriz da seção 3.1 — a restrição não pode viver apenas no app, já que o cliente é substituível.
- Escopo do professor limitado às suas turmas/disciplinas, validado no servidor.
- Perfil e vínculos (responsável→alunos, professor→turmas) definidos no servidor, nunca informados pelo cliente.
- Contas criadas apenas pela secretaria, sem autocadastro.

### 6.3 Conformidade legal

> ⚠️ **Pendência com impacto em lançamento.** Foi decidido não tratar requisitos de LGPD nesta fase. Registro o que isso implica:
>
> - A **Play Store exige política de privacidade** e o preenchimento da seção *Data Safety* para publicar qualquer app que colete dados pessoais. Sem isso, a publicação é recusada.
> - A LGPD (art. 14) exige consentimento de ao menos um dos pais para o tratamento de dados de crianças.
> - Sem **log de auditoria**, o colégio não consegue comprovar quem lançou ou alterou uma nota, falta, ocorrência ou cobrança — o que costuma ser decisivo em contestações de famílias.
>
> Nada disso bloqueia o desenvolvimento agora, mas deve ser resolvido antes da FASE 7 (Deploy). Ver tasks correspondentes em `TASKS.md`.

Sem proteções adicionais no dispositivo: sem biometria, sem PIN, sem bloqueio de screenshot, sem logout automático por inatividade.

---

## 7. Design e Identidade Visual

- O Colégio IBPI **possui identidade visual oficial** (logo e cores). O `core:designsystem` será construído sobre ela.
- **Ícones:** Material Icons para a interface; logo e imagens fornecidos pelo colégio.
- **Formato:** celular em retrato, tema claro apenas.
- Componentes Compose pequenos e sem regra de negócio, com previews.
- A `MainActivity` chama `enableEdgeToEdge()`. A partir do `targetSdk 35` o desenho edge-to-edge é obrigatório, e sem essa chamada o sistema desenha ícones claros na barra de status — ilegíveis sobre o tema claro do app.

> ⚠️ A definir: arquivos do **logo** (SVG/PNG) e os **códigos hexadecimais** das cores oficiais.
> ⚠️ A definir: existe protótipo, layout ou referência visual (Figma, print, app de referência)? Sem isso, as telas seguirão Material 3 padrão com a paleta do colégio, e o refinamento visual acontece na FASE 4.

---

## 8. Integrações Externas

**Nenhuma integração externa no escopo inicial.** Apenas os serviços do Firebase.

Fica explicitamente fora: gateway de pagamento, e-mail transacional além do padrão do Firebase Auth, WhatsApp Business API e qualquer ERP acadêmico.

---

## 9. Infraestrutura

### 9.1 Firebase

- **Projeto ainda não criado** — a criação é a primeira task da FASE 1.
- Serviços: Authentication (e-mail/senha), Cloud Firestore, Storage.
- **Ambiente único** — um único projeto Firebase para desenvolvimento e produção.

> ⚠️ Risco aceito: sem separação dev/produção, testes durante o desenvolvimento manipulam a mesma base que as famílias usam. Recomendo revisar essa decisão antes de o app entrar em uso real.

- `google-services.json` fica **fora do controle de versão** (`.gitignore`).

### 9.2 Repositório

- GitHub: `ColegioIBPI/ibpi_app_android`
- Branch única: `main`.
- CI no GitHub Actions (`.github/workflows/ci.yml`) roda testes unitários e build de debug a cada push e pull request.

**Duas armadilhas de trabalhar no Windows com CI Linux**, já resolvidas mas que voltam a cada script novo:

- **Bit de execução.** O Windows não tem permissão de execução, então arquivos como o `gradlew` entram no git com modo `100644` e o runner Ubuntu falha com *Permission denied*. Corrija com `git update-index --chmod=+x <arquivo>` antes de commitar qualquer script `.sh`.
- **Finais de linha.** O `.gitattributes` força LF no `gradlew` e nos `.sh`; sem isso, um checkout com CRLF quebra o shell do CI com *bad interpreter*.

### 9.3 Painel web administrativo

A gestão estrutural (cadastro de turmas, disciplinas, matrículas, vínculos e importação em lote de usuários) acontecerá em um **painel web separado**, tratado como outro projeto. Enquanto ele não existe, esses cadastros são feitos por **scripts de importação e pelo console do Firebase**.

> ⚠️ A definir: quando o painel web entra, e qual ferramenta a secretaria usará para a importação em lote no intervalo.

---

## 10. Padrões de Desenvolvimento

### 10.1 Arquitetura

- **MVVM** com Clean Architecture pragmática: `presentation`, `domain`, `data`.
- Estado exposto por `StateFlow` imutável, com loading, sucesso, vazio e erro explícitos.
- `UiAction` para intenção do usuário, `UiEvent` para efeitos únicos (navegação, snackbar).
- Regra de negócio fora dos composables; contratos de repositório no domínio, implementação no data.
- Sem dependência entre detalhes internos de features.

### 10.2 Commits (Conventional Commits)

| Prefixo | Uso |
|---|---|
| `feat:` | nova funcionalidade |
| `fix:` | correção de bug |
| `docs:` | alteração em documentação |
| `test:` | adição ou ajuste de testes |
| `style:` | formatação, sem mudança de lógica |
| `refactor:` | refatoração de código |
| `chore:` | tarefas gerais (configs, dependências) |

### 10.3 Branches

**Estratégia adotada: commit direto em `main`.** Sem `develop`, sem branches de feature.

> Escolha consciente do time para reduzir cerimônia no início. Quando um segundo desenvolvedor entrar no projeto, vale migrar para `main` + `feature/*` com Pull Request.

### 10.4 Testes

Testes são obrigatórios em toda feature e toda mudança relevante:

- Testes unitários de ViewModel (sucesso, loading, erro, vazio, retry).
- Testes unitários de use case e regras de escopo/permissão.
- Testes de repositório quando a lógica não é trivial.
- Testes de UI em Compose para os fluxos críticos (login, consulta de boletim, lançamento de frequência).

**Testes de Compose rodam na JVM, via Robolectric** — sem emulador e sem aparelho conectado, o que permite executá-los no CI e em `./gradlew testDebugUnitTest`. Para isso o módulo `app` habilita `testOptions { unitTests { isIncludeAndroidResources = true } }`.

Ao verificar o conteúdo de uma tela, prefira asserir textos **únicos** na árvore. Títulos como "Boletim" e "Financeiro" se repetem no rótulo da aba, e `onNodeWithText` falha com múltiplos nós. Os itens da barra inferior expõem a tag `bottom_bar_item_<rota>` justamente para permitir uma seleção não ambígua.

Comandos:

```bash
./gradlew testDebugUnitTest
```

### 10.5 Documentação

Toda mudança de comportamento, arquitetura, contrato de dados ou navegação atualiza a documentação na mesma entrega. Documentação pendente significa task não concluída.

---

## Pendências consolidadas

| # | Pendência | Bloqueia |
|---|---|---|
| 1 | Sistema de avaliação (períodos, médias, recuperação, diferenças por segmento) | Modelagem e telas do Boletim (FASE 3) |
| 2 | Logo e cores oficiais do colégio | Design system (FASE 1/4) |
| 3 | Protótipo ou referência visual | Refinamento de UI (FASE 4) |
| 4 | Decisão sobre notificações push | Escopo futuro |
| 5 | Política de privacidade e Data Safety | Publicação na Play Store (FASE 7) |
| 6 | Um aluno pode ter múltiplos responsáveis? | Modelagem de vínculos (FASE 3) |
| 7 | Ferramenta de importação em lote pela secretaria | Carga inicial de usuários (FASE 3) |
| 8 | Separação de ambientes dev/produção | Entrada em uso real |

---

*Documento gerado na fase de levantamento de requisitos. Mantido atualizado conforme o projeto evolui.*
