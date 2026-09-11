# MyIBPI — Tasks de Desenvolvimento

Plano de execução derivado do levantamento de requisitos (`README.md`).
Cada task deve caber em uma sessão de trabalho. Toda task de código inclui **testes** e **atualização de documentação** — sem isso, não está concluída.

---

## [FASE 1] Setup e Configuração

- [x] Alinhar a toolchain e destravar o build *(AGP 8.6.0, Gradle 8.7, Kotlin 2.0.21, compileSdk/targetSdk 35, JVM 17)*
- [x] Migrar o template de Views para Compose (removidos `appcompat` e `material` de Views)
- [x] Configurar `gradle/libs.versions.toml` com Compose BOM, Hilt, KSP, Navigation e libs de teste
- [x] Configurar Hilt (`@HiltAndroidApp` em `MyIbpiApplication`, `@AndroidEntryPoint` em `MainActivity`)
- [x] Criar módulo `core:designsystem` com o tema IBPI (paleta provisória, tipografia)
- [x] Criar módulo `core:ui` (componente de placeholder de feature)
- [x] Criar módulo `core:navigation` (enum `MainDestination` com as 5 abas)
- [x] Criar os 5 módulos de feature com telas placeholder
- [x] Declarar `MyIbpiApplication` e `MainActivity` no `AndroidManifest.xml`, com launcher
- [x] Configurar Navigation Compose com a bottom bar de 5 abas
- [x] Configurar GitHub Actions para build e testes a cada push
- [x] Incluir `google-services.json` no `.gitignore`
- [ ] Criar projeto no Firebase e habilitar Authentication (e-mail/senha), Firestore e Storage
- [ ] Adicionar `google-services.json` em `app/` e aplicar o plugin `google-services`
- [ ] Criar módulo `core:firebase` (providers de Auth, Firestore e Storage)
- [ ] Primeiro commit e push para `ColegioIBPI/ibpi_app_android`
- [ ] Aplicar logo e cores oficiais do colégio no `core:designsystem`
- [ ] Atualizar o Android Studio para uma versão 2025.x e, junto, subir para AGP 8.13.x + compileSdk 36 + Kotlin 2.4.x
- [ ] Criar convention plugins do Gradle quando a duplicação entre módulos justificar
- [ ] Criar módulo `core:common` quando houver utilitário compartilhado real
- [ ] Criar módulo `core:testing` junto com os primeiros ViewModels

> ⚠️ Dependência externa: logo e cores oficiais do colégio. Enquanto não chegam, o `core:designsystem` usa uma paleta provisória isolada em `theme/Color.kt`, trocável sem tocar em nenhuma tela.

---

## [FASE 2] Autenticação e Sessão

- [ ] Modelar `User`, `UserProfile` (aluno, responsável, professor, secretaria, coordenação, financeiro) e vínculos no Firestore
- [ ] Implementar `AuthRepository` (contrato no domínio, implementação com Firebase Auth)
- [ ] Tela de Login (e-mail + senha) com validação e estados de erro
- [ ] Tela de Recuperação de Senha
- [ ] Sessão persistente e splash de roteamento por perfil
- [ ] Montagem dinâmica das abas conforme o perfil (aba Financeiro oculta para aluno)
- [ ] Seletor de aluno para responsáveis com mais de um filho, propagando o contexto para todas as abas
- [ ] Tela/fluxo de logout
- [ ] Escrever regras de segurança do Firestore refletindo a matriz de acesso do README (seção 3.1)
- [ ] Testes unitários do `AuthViewModel` (sucesso, credencial inválida, offline, loading)
- [ ] Testes unitários das regras de escopo por perfil
- [ ] Teste de UI do fluxo de login

> Sem tela de cadastro: contas são criadas pela secretaria.

---

## [FASE 3] Funcionalidades Core

### 3.1 Modelagem acadêmica base

- [ ] Modelar `Segment` (EF, EM, EJA, Cursos Livres), `SchoolClass` (turma), `Subject` (disciplina), `AcademicTerm` (período letivo)
- [ ] Modelar `Student` e `Enrollment` (matrícula)
- [ ] Modelar vínculo `Guardian ↔ Student` (muitos-para-muitos)
- [ ] Modelar vínculo `Teacher ↔ SchoolClass/Subject` (base do escopo do professor)
- [ ] Script de importação em lote de alunos, responsáveis, turmas e vínculos
- [ ] Documentar o contrato de dados do Firestore (coleções, campos, índices)
- [ ] Testes das regras de vínculo e escopo

### 3.2 Frequência e Ocorrências (`feature:attendance`)

- [ ] Modelar `AttendanceRecord` (presença/falta) e `Incident` (disciplinar / acadêmica)
- [ ] `AttendanceRepository` — contrato no domínio e implementação Firestore
- [ ] Use case de consulta com filtro por tipo (frequência · disciplinar · acadêmica)
- [ ] Tela de consulta com lista cronológica, filtro e detalhe do registro
- [ ] Ocultar ocorrências no perfil aluno (aba exibe apenas frequência)
- [ ] Tela de lançamento de frequência da turma pelo professor
- [ ] Tela de lançamento de ocorrência individual pelo professor
- [ ] Restringir turmas/disciplinas do professor às que ele leciona (app + regras do Firestore)
- [ ] Testes de ViewModel: sucesso, vazio, erro, filtro, permissão negada
- [ ] Teste de UI do lançamento de frequência

### 3.3 Boletim (`feature:report`)

> 🚫 **Bloqueada** até a definição do sistema de avaliação (pendência 1 do README).

- [ ] Definir com o colégio: períodos, quantidade de avaliações, cálculo de média, nota de aprovação, recuperação e diferenças entre segmentos
- [ ] Modelar `Grade`, `SubjectPerformance` e `ReportCard`
- [ ] `ReportCardRepository` — contrato e implementação
- [ ] Use case de montagem do boletim por período letivo
- [ ] Tela de boletim por disciplina e período
- [ ] Testes de ViewModel e do cálculo de médias

### 3.4 Avisos (`feature:announcements`)

- [ ] Modelar `Announcement` com alvo: aluno · responsável · turma · segmento · todos
- [ ] `AnnouncementRepository` — contrato e implementação
- [ ] Use case de resolução do público-alvo (quais avisos cada usuário enxerga)
- [ ] Tela de lista de avisos com estado de lido/não lido
- [ ] Tela de detalhe do comunicado
- [ ] Tela de publicação com seletor de destinatário (secretaria e coordenação)
- [ ] Testes da resolução de público-alvo para cada combinação de perfil e alvo
- [ ] Teste de UI da publicação

### 3.5 Financeiro (`feature:finance`)

- [ ] Modelar `Charge` (mensalidade: valor, vencimento, situação), `Payment` e anexos
- [ ] `FinanceRepository` — contrato e implementação com Firestore + Storage
- [ ] Tela de extrato de mensalidades (em aberto · pago · vencido)
- [ ] Download e compartilhamento do PDF do boleto
- [ ] Copiar linha digitável / código de barras
- [ ] Exibição do PIX copia-e-cola e geração do QR Code
- [ ] Tela de histórico de pagamentos com comprovantes
- [ ] Tela de lançamento de cobrança, upload de boleto e baixa de pagamento (perfil financeiro)
- [ ] Bloquear a aba inteira para o perfil aluno (app + regras do Firestore)
- [ ] Testes de ViewModel: extrato vazio, parcela vencida, erro de download, permissão negada

### 3.6 Informações Úteis (`feature:info`)

- [ ] Modelar `InfoCard` com tipo de escopo: turma · segmento · aluno · global
- [ ] `InfoRepository` — contrato e implementação com Firestore + Storage
- [ ] Use case de filtragem dos cards visíveis para o usuário
- [ ] Tela em grade de cards
- [ ] Abertura do conteúdo (PDF/link) a partir do card
- [ ] Gestão de conteúdo e upload de arquivos (secretaria e coordenação)
- [ ] Cobrir os 8 cards: horário das aulas, calendário de avaliação, calendário escolar, critérios de avaliação, proposta pedagógica, dependências, eletivas, tutoria
- [ ] Testes da filtragem por escopo

---

## [FASE 4] Design e UI

- [ ] Aplicar logo e paleta oficiais no `core:designsystem`
- [ ] Padronizar tipografia e espaçamentos
- [ ] Ícone do app e splash screen com a marca do IBPI
- [ ] Revisar estados vazios, de erro e de carregamento em todas as telas
- [ ] Previews de Compose para os componentes reutilizáveis
- [ ] Revisão visual das 5 abas com o colégio

---

## [FASE 5] Testes

- [ ] Testes unitários de todos os ViewModels e use cases
- [ ] Testes de integração dos repositórios contra o emulador do Firebase
- [ ] Testes das regras de segurança do Firestore (Firebase Rules Unit Testing)
- [ ] Testes de UI dos fluxos críticos: login, consulta de boletim, lançamento de frequência, publicação de aviso, extrato financeiro
- [ ] Teste de matriz de permissões: cada perfil vê exatamente o que deve ver
- [ ] Relatório de cobertura e execução dos testes no CI

---

## [FASE 6] Documentação

- [ ] Manter `README.md` alinhado ao código conforme as fases avançam
- [ ] Documentar o contrato de dados do Firestore (coleções, campos, índices, regras)
- [ ] Documentar cada módulo (responsabilidade e dependências)
- [ ] Documentar o grafo de navegação e as rotas
- [ ] Documentar decisões técnicas relevantes (ADRs curtos)
- [ ] Guia de setup para um novo desenvolvedor (Firebase, `google-services.json`, build)

---

## [FASE 7] Deploy

- [ ] Redigir política de privacidade e publicá-la em URL pública
- [ ] Preencher a seção Data Safety da Play Console
- [ ] Tratar o consentimento parental exigido pela LGPD para dados de menores
- [ ] Configurar assinatura de release e `minify`/R8
- [ ] Criar a conta/ficha do app na Google Play Console
- [ ] Publicar em teste interno com a equipe do colégio
- [ ] Piloto com um grupo restrito de famílias
- [ ] Publicação em produção e validação final

> As três primeiras tasks desta fase são as pendências de LGPD adiadas na Etapa 5 do levantamento. Elas são **bloqueantes para a publicação**, não opcionais.

---

## Backlog (fora do escopo atual)

- Notificações push via Firebase Cloud Messaging
- Painel web administrativo
- Integração com gateway de pagamento (boleto e PIX automáticos)
- Modo offline com sincronização de lançamentos do professor
- Layout para tablet e modo escuro
- App iOS reaproveitando o backend
- Separação de ambientes dev/produção
