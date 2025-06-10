// js/script.js

// ----------------------------------------------------------------------
// CONFIGURAÇÃO DO FORMULÁRIO DE BUSCA (JSON)
// Esta estrutura JSON define os campos do formulário, suas propriedades e regras.
// Ela é a 'fonte da verdade' para a estrutura do formulário.
// ----------------------------------------------------------------------
const formConfig = {
  "title": "Questionário de Busca de Passagens",
  "description": "Formulário para encontrar passagens aéreas.",
  "fields": [
    {
      "id": "origem",
      "label": "Origem",
      "type": "text",
      "placeholder": "Cidade de partida",
      "required": true,
      "browser_autocomplete": "off", // Indicador para o navegador (HTML)
      "autocomplete_suggestions": [
        "Rio de Janeiro (GIG)",
        "Rio de Janeiro (SDU)",
        "São Paulo (GRU)",
        "São Paulo (CGH)",
        "Brasília (BSB)",
        "Salvador (SSA)",
        "Fortaleza (FOR)",
        "Belo Horizonte (CNF)",
        "Recife (REC)",
        "Porto Alegre (POA)",
        "Manaus (MAO)",
        "Curitiba (CWB)",
        "Lisboa (LIS)"
      ]
    },
    {
      "id": "destino",
      "label": "Destino",
      "type": "text",
      "placeholder": "Cidade de chegada",
      "required": true,
      "browser_autocomplete": "off", // Indicador para o navegador (HTML)
      "autocomplete_suggestions": [
        "Rio de Janeiro (GIG)",
        "Rio de Janeiro (SDU)",
        "São Paulo (GRU)",
        "São Paulo (CGH)",
        "Brasília (BSB)",
        "Salvador (SSA)",
        "Fortaleza (FOR)",
        "Recife (REC)",
        "Porto Alegre (POA)",
        "Manaus (MAO)",
        "Curitiba (CWB)",
        "Lisboa (LIS)"
      ]
    },
    {
      "id": "data-ida",
      "label": "Data de Ida",
      "type": "text", // ALTERADO: agora é um campo de texto para permitir digitação formatada
      "placeholder": "dd/mm/aaaa", // Adicionado para guiar o usuário
      "required": true,
      "browser_autocomplete": "off" // Indicador para o navegador (HTML)
    },
    {
      "id": "data-volta",
      "label": "Data de Volta",
      "type": "text", // ALTERADO: agora é um campo de texto para permitir digitação formatada
      "placeholder": "dd/mm/aaaa", // Adicionado para guiar o usuário
      "required": false, // Será controlado via JS, mas o padrão é não obrigatório
      "optional_text": "(Opcional)",
      "browser_autocomplete": "off", // Indicador para o navegador (HTML)
      "conditional_visibility": {
        "depends_on": "tipo-viagem",
        "show_if_value_is_not": "one-way",
        "set_required_when_visible": true // Adicionado para deixar explícito
      }
    },
    {
      "id": "tipo-viagem",
      "label": "Tipo de Viagem",
      "type": "select",
      "required": false,
      "options": [
        {
          "value": "round-trip",
          "text": "Ida e Volta"
        },
        {
          "value": "one-way",
          "text": "Só Ida"
        }
      ]
    }
  ],
  "submit_button": {
    "text": "Buscar"
  }
};


// ----------------------------------------------------------------------
// DADOS DE EXEMPLO (MOCK DATA)
// Em um projeto real, esses dados viriam de uma API ou backend.
// ----------------------------------------------------------------------
const voosDisponiveis = [
    {
        id: 1,
        companhia: "Latam Airlines",
        logo: "assets/img/logo-latam.png",
        horaPartida: "10:00",
        horaChegada: "13:30",
        duracao: "3h 30m",
        paradas: "Direto",
        origem: "São Paulo (GRU)",
        destino: "Rio de Janeiro (SDU)",
        preco: 450,
        numParadas: 0
    },
    {
        id: 2,
        companhia: "GOL Linhas Aéreas",
        logo: "assets/img/logo-gol.png",
        horaPartida: "11:15",
        horaChegada: "15:00",
        duracao: "3h 45m",
        paradas: "1 Parada",
        origem: "São Paulo (GRU)",
        destino: "Rio de Janeiro (SDU)",
        preco: 380,
        numParadas: 1
    },
    {
        id: 3,
        companhia: "Azul Linhas Aéreas",
        logo: "assets/img/logo-azul.png",
        horaPartida: "09:30",
        horaChegada: "12:45",
        duracao: "3h 15m",
        paradas: "Direto",
        origem: "São Paulo (GRU)",
        destino: "Rio de Janeiro (SDU)",
        preco: 510,
        numParadas: 0
    },
    {
        id: 4,
        companhia: "Latam Airlines",
        logo: "assets/img/logo-latam.png",
        horaPartida: "14:00",
        horaChegada: "18:00",
        duracao: "4h 00m",
        paradas: "1 Parada",
        origem: "Rio de Janeiro (SDU)",
        destino: "São Paulo (GRU)",
        preco: 1200,
        numParadas: 1
    },
    {
        id: 5,
        companhia: "GOL Linhas Aéreas",
        logo: "assets/img/logo-gol.png",
        horaPartida: "16:00",
        horaChegada: "20:00",
        duracao: "4h 00m",
        paradas: "2 Paradas",
        origem: "São Paulo (GRU)",
        destino: "Brasília (BSB)",
        preco: 2500,
        numParadas: 2
    },
    {
        id: 6,
        companhia: "Azul Linhas Aéreas",
        logo: "assets/img/logo-azul.png",
        horaPartida: "07:00",
        horaChegada: "08:30",
        duracao: "1h 30m",
        paradas: "Direto",
        origem: "Belo Horizonte (CNF)",
        destino: "Rio de Janeiro (SDU)",
        preco: 320,
        numParadas: 0
    },
    {
        id: 7,
        companhia: "TAP Air Portugal",
        logo: "assets/img/logo-tap.png",
        horaPartida: "22:00",
        horaChegada: "10:00 (+1)",
        duracao: "12h 00m",
        paradas: "1 Parada",
        origem: "São Paulo (GRU)",
        destino: "Lisboa (LIS)",
        preco: 4800,
        numParadas: 1
    },
];

// ----------------------------------------------------------------------
// POPULAÇÃO DINÂMICA DA LISTA DE AEROPORTOS PARA AUTOCOMPLETE
// Extrai as sugestões de autocomplete do `formConfig`
// ----------------------------------------------------------------------
const aeroportos = [];
const origemFieldConfig = formConfig.fields.find(field => field.id === 'origem');
if (origemFieldConfig && origemFieldConfig.autocomplete_suggestions) {
    origemFieldConfig.autocomplete_suggestions.forEach(suggestion => {
        const match = suggestion.match(/(.+)\s+$$(.+)$$/); // Captura "Cidade" e "SIGLA"
        if (match) {
            aeroportos.push({
                nome: suggestion,
                cidade: match[1].trim(),
                sigla: match[2].trim()
            });
        } else {
            // Caso não siga o padrão (Cidade (SIGLA)), adicione como está
            aeroportos.push({
                nome: suggestion,
                cidade: suggestion,
                sigla: ''
            });
        }
    });
}


// ----------------------------------------------------------------------
// LÓGICA PRINCIPAL DO SCRIPT (EXECUTADA APÓS O CARREGAMENTO DO DOM)
// ----------------------------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    // --- Lógica para a página inicial (index.html) ---
    const formPesquisa = document.getElementById('form-pesquisa');
    const inputOrigem = document.getElementById('origem');
    const inputDestino = document.getElementById('destino');
    const inputDataIda = document.getElementById('data-ida'); // Obter referência ao campo de data de ida
    const inputDataVolta = document.getElementById('data-volta'); // Obter referência ao campo de data de volta
    const tipoViagemSelect = document.getElementById('tipo-viagem');
    const divDataVolta = document.getElementById('div-data-volta');
    const mensagemErroContainer = document.getElementById('mensagem-erro-formulario');
    const mensagemErroParagrafo = mensagemErroContainer ? mensagemErroContainer.querySelector('p') : null;

    // Obtém as configurações específicas do JSON para os campos 'data-volta' e 'tipo-viagem'
    const dataVoltaFieldConfig = formConfig.fields.find(field => field.id === 'data-volta');
    const tipoViagemFieldConfig = formConfig.fields.find(field => field.id === 'tipo-viagem');


    if (formPesquisa) {
        // Setup do autocomplete para Origem e Destino
        setupAutocomplete(inputOrigem, 'origem-suggestions');
        setupAutocomplete(inputDestino, 'destino-suggestions');

        // Função para controlar a visibilidade e o atributo 'required' da Data de Volta
        function toggleDataVoltaVisibility() {
            // Utiliza as regras de visibilidade definidas no `formConfig`
            if (dataVoltaFieldConfig && dataVoltaFieldConfig.conditional_visibility) {
                const dependsOnId = dataVoltaFieldConfig.conditional_visibility.depends_on;
                const showIfValueIsNot = dataVoltaFieldConfig.conditional_visibility.show_if_value_is_not;
                const setRequiredWhenVisible = dataVoltaFieldConfig.conditional_visibility.set_required_when_visible;

                const controllingField = document.getElementById(dependsOnId);

                if (controllingField && controllingField.value === showIfValueIsNot) {
                    divDataVolta.style.display = 'none';
                    inputDataVolta.removeAttribute('required'); // Remove required se escondido
                } else {
                    divDataVolta.style.display = 'block';
                    if (setRequiredWhenVisible) {
                        inputDataVolta.setAttribute('required', 'true'); // Adiciona required se visível e configurado
                    } else {
                        inputDataVolta.removeAttribute('required'); // Garante que não seja required se não configurado
                    }
                }
            } else {
                // Fallback (mantido caso o JSON não esteja completo ou haja um erro na configuração)
                if (tipoViagemSelect.value === 'one-way') {
                    divDataVolta.style.display = 'none';
                    inputDataVolta.removeAttribute('required');
                } else {
                    divDataVolta.style.display = 'block';
                    inputDataVolta.setAttribute('required', 'true');
                }
            }
        }

        // Event Listener para o campo Tipo de Viagem (para controlar a Data de Volta)
        tipoViagemSelect.addEventListener('change', toggleDataVoltaVisibility);

        // Chamar a função uma vez ao carregar a página para definir o estado inicial
        toggleDataVoltaVisibility();

        // ----------------------------------------------------------------------
        // FUNÇÃO DE FORMATAÇÃO E VALIDAÇÃO DE DATA (DDMMYY / DDMMYYYY para DD/MM/YYYY)
        // ----------------------------------------------------------------------
        function formatAndValidateDate(inputString) {
            // Remove todos os caracteres não-dígitos
            const cleanString = inputString.replace(/\D/g, '');

            let day, month, year;

            if (cleanString.length === 6) { // Formato DDMMYY
                day = parseInt(cleanString.substring(0, 2), 10);
                month = parseInt(cleanString.substring(2, 4), 10);
                year = parseInt(cleanString.substring(4, 6), 10);

                // Converte ano de 2 dígitos para 4 dígitos (ex: 25 -> 2025, 90 -> 1990)
                year = (year < 50) ? (2000 + year) : (1900 + year);

            } else if (cleanString.length === 8) { // Formato DDMMYYYY
                day = parseInt(cleanString.substring(0, 2), 10);
                month = parseInt(cleanString.substring(2, 4), 10);
                year = parseInt(cleanString.substring(4, 8), 10);
            } else {
                return ''; // Formato de entrada inválido ou incompleto
            }

            // Validação básica de faixa (dia e mês)
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1000) { // Ano mínimo para evitar datas absurdas
                return ''; // Data inválida
            }

            // Cria um objeto Date para validação completa (ex: 31/02, 31/04)
            const date = new Date(year, month - 1, day); // month - 1 porque o mês é baseado em 0 (jan=0, fev=1, etc)

            // Verifica se os componentes do objeto Date batem com os que foram passados.
            // Se o dia no objeto Date for diferente, significa que a data original era inválida
            // (ex: new Date(2023, 1, 31) -> 31 de fevereiro, se torna 3 de março de 2023)
            if (date.getDate() === day && date.getMonth() === month - 1 && date.getFullYear() === year) {
                // Formata os componentes para terem sempre 2 dígitos (ex: 1 -> 01)
                const formattedDay = String(day).padStart(2, '0');
                const formattedMonth = String(month).padStart(2, '0');
                return `${formattedDay}/${formattedMonth}/${year}`;
            } else {
                return ''; // Data inválida (ex: 31/02/2023)
            }
        }

        // Adiciona event listeners para formatação no 'blur' dos campos de data
        if (inputDataIda) {
            inputDataIda.addEventListener('blur', () => {
                inputDataIda.value = formatAndValidateDate(inputDataIda.value);
            });
        }
        if (inputDataVolta) { // Verifica se existe, pois é condicional
            inputDataVolta.addEventListener('blur', () => {
                inputDataVolta.value = formatAndValidateDate(inputDataVolta.value);
            });
        }


        // Event Listener para o envio do formulário de pesquisa
        formPesquisa.addEventListener('submit', (event) => {
            event.preventDefault(); // Impede o envio padrão do formulário

            // Validação do formulário (usa a validação nativa do HTML5 e a validação de data)
            if (!formPesquisa.checkValidity()) {
                if (mensagemErroParagrafo) {
                    mensagemErroParagrafo.textContent = 'Por favor, preencha todos os campos obrigatórios.';
                    mensagemErroContainer.classList.remove('hidden'); // Mostra o alerta
                }
                // Opcional: rolar para o primeiro campo inválido para melhor UX
                const primeiroInvalido = formPesquisa.querySelector(':invalid');
                if (primeiroInvalido) {
                    primeiroInvalido.focus();
                    primeiroInvalido.scrollIntoView({ behavior: 'smooth', block: 'center' });
                }
                return; // Impede a continuação do envio do formulário
            } else {
                // Validação customizada para os campos de data formatados
                // Garante que mesmo que o usuário tente submeter sem sair do campo, a data seja validada
                if (inputDataIda && !formatAndValidateDate(inputDataIda.value)) {
                    if (mensagemErroParagrafo) {
                        mensagemErroParagrafo.textContent = 'Por favor, insira uma Data de Ida válida (DD/MM/AAAA).';
                        mensagemErroContainer.classList.remove('hidden');
                    }
                    inputDataIda.focus();
                    return;
                }
                if (tipoViagemSelect.value !== 'one-way' && inputDataVolta && !formatAndValidateDate(inputDataVolta.value)) {
                    if (mensagemErroParagrafo) {
                        mensagemErroParagrafo.textContent = 'Por favor, insira uma Data de Volta válida (DD/MM/AAAA).';
                        mensagemErroContainer.classList.remove('hidden');
                    }
                    inputDataVolta.focus();
                    return;
                }


                if (mensagemErroContainer) {
                    mensagemErroContainer.classList.add('hidden'); // Esconde o alerta se o formulário for válido
                }
            }

            // Se a validação passou, captura os valores e redireciona
            const origem = inputOrigem.value;
            const destino = inputDestino.value;
            const dataIda = inputDataIda.value; // Pega o valor já formatado
            let dataVolta = '';
            // Só captura a data de volta se o campo estiver visível (não for "Só Ida")
            if (tipoViagemSelect.value !== 'one-way') {
                dataVolta = inputDataVolta.value; // Pega o valor já formatado
            }
            const tipoViagem = tipoViagemSelect.value;

            // Armazena os dados da busca no localStorage para a página de resultados
            localStorage.setItem('dadosBusca', JSON.stringify({
                origem,
                destino,
                dataIda,
                dataVolta,
                tipoViagem
            }));

            // Redireciona para a página de resultados
            window.location.href = 'resultados.html';
        });
    }


    // ----------------------------------------------------------------------
    // FUNÇÃO GENÉRICA DE SETUP PARA AUTOCOMPLETE - COM MELHORIA APLICADA
    // ----------------------------------------------------------------------
    function setupAutocomplete(inputElement, suggestionsUniqueId) {
        let suggestionsContainer = document.getElementById(suggestionsUniqueId);
        // Cria o container de sugestões se ele não existir
        if (!suggestionsContainer) {
            suggestionsContainer = document.createElement('div');
            suggestionsContainer.id = suggestionsUniqueId;
            // Posição absoluta para sobrepor outros elementos
            suggestionsContainer.className = 'absolute z-30 bg-white border border-gray-300 rounded-md shadow-lg max-h-60 overflow-y-auto mt-1 w-full';
            inputElement.parentNode.insertBefore(suggestionsContainer, inputElement.nextSibling);
        }

        // Variável para armazenar o último valor válido selecionado/digitado
        let lastValidSelection = '';

        inputElement.addEventListener('input', () => {
            // Se o usuário começar a digitar, a seleção anterior é invalidada
            lastValidSelection = '';
            const searchTerm = inputElement.value.toLowerCase();
            suggestionsContainer.innerHTML = ''; // Limpa sugestões anteriores

            if (searchTerm.length < 2) { // Começa a sugerir após 2 caracteres
                suggestionsContainer.style.display = 'none';
                return;
            }

            // Filtra os aeroportos com base no termo de busca
            const filteredAeroportos = aeroportos.filter(aeroporto =>
                aeroporto.nome.toLowerCase().includes(searchTerm) ||
                aeroporto.cidade.toLowerCase().includes(searchTerm) ||
                aeroporto.sigla.toLowerCase().includes(searchTerm)
            );

            if (filteredAeroportos.length > 0) {
                suggestionsContainer.style.display = 'block';
                filteredAeroportos.forEach(aeroporto => {
                    const suggestionItem = document.createElement('div');
                    suggestionItem.className = 'p-2 cursor-pointer hover:bg-blue-100 text-gray-800';
                    suggestionItem.textContent = `${aeroporto.nome}`; // Exibe apenas o nome completo (ex: "São Paulo (GRU)")
                    suggestionItem.addEventListener('click', () => {
                        inputElement.value = aeroporto.nome;
                        lastValidSelection = aeroporto.nome; // Armazena a seleção válida
                        suggestionsContainer.style.display = 'none';
                    });
                    suggestionsContainer.appendChild(suggestionItem);
                });
            } else {
                suggestionsContainer.style.display = 'none';
            }
        });

        // Validar e limpar o campo quando ele perde o foco
        inputElement.addEventListener('blur', () => {
            // Pequeno atraso para permitir que o clique na sugestão seja processado antes do blur
            setTimeout(() => {
                suggestionsContainer.style.display = 'none'; // Esconde as sugestões

                // Verifica se o valor atual do input é diferente da última seleção válida
                // OU se o valor atual não corresponde a NENHUM nome de aeroporto válido (digitado manualmente e corretamente)
                if (inputElement.value !== lastValidSelection && !aeroportos.some(a => a.nome === inputElement.value)) {
                    inputElement.value = ''; // Limpa o campo
                }
            }, 100);
        });
    }


    // ----------------------------------------------------------------------
    // LÓGICA PARA A PÁGINA DE RESULTADOS (resultados.html)
    // ----------------------------------------------------------------------
    const listaPassagens = document.getElementById('lista-passagens');
    const filtroPrecoInput = document.getElementById('filtro-preco');
    const valorPrecoSpan = document.getElementById('valor-preco');
    const filtroCompanhiaSelect = document.getElementById('filtro-companhia');
    const filtroParadasSelect = document.getElementById('filtro-paradas');
    const aplicarFiltrosBtn = document.getElementById('aplicar-filtros-btn');
    const limparFiltrosBtn = document.getElementById('limpar-filtros-btn');


    if (listaPassagens) { // Só executa se estiver na página de resultados
        // Função para renderizar os voos na lista
        function renderizarVoos(voosParaExibir) {
            listaPassagens.innerHTML = ''; // Limpa os voos existentes
            if (voosParaExibir.length === 0) {
                listaPassagens.innerHTML = '<p class="text-center text-gray-600 text-lg py-8">Nenhum voo encontrado com os filtros selecionados.</p>';
                return;
            }

            voosParaExibir.forEach(voo => {
                const vooCard = `
                    <div class="bg-white p-6 rounded-lg shadow-md flex flex-col md:flex-row items-center justify-between gap-6">
                        <div class="flex-grow">
                            <div class="flex items-center mb-2">
                                <img src="${voo.logo}" alt="Logo ${voo.companhia}" class="h-8 w-auto mr-3">
                                <span class="text-lg font-semibold">${voo.companhia}</span>
                            </div>
                            <div class="flex items-center text-gray-600 text-sm mb-1">
                                <span class="font-medium">${voo.horaPartida} - ${voo.horaChegada}</span> &bull; Duração: ${voo.duracao} &bull; ${voo.paradas}
                            </div>
                            <p class="text-gray-700">${voo.origem} → ${voo.destino}</p>
                        </div>
                        <div class="text-right">
                            <span class="text-3xl font-bold text-green-600">R$ ${voo.preco}</span>
                            <button class="block w-full mt-2 bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md transition duration-300">Selecionar</button>
                        </div>
                    </div>
                `;
                listaPassagens.insertAdjacentHTML('beforeend', vooCard);
            });
        }

        // Função para aplicar os filtros de preço, companhia e paradas
        function aplicarFiltros() {
            const precoMaximo = parseInt(filtroPrecoInput.value);
            const companhiaSelecionada = filtroCompanhiaSelect.value;
            const paradasSelecionadas = filtroParadasSelect.value;

            let voosFiltrados = voosDisponiveis.filter(voo => {
                // Filtro por preço
                if (voo.preco > precoMaximo) {
                    return false;
                }
                // Filtro por companhia aérea
                if (companhiaSelecionada !== 'Todas' && voo.companhia !== companhiaSelecionada) {
                    return false;
                }
                // Filtro por paradas
                if (paradasSelecionadas === 'Direto' && voo.numParadas !== 0) {
                    return false;
                }
                if (paradasSelecionadas === '1 Parada' && voo.numParadas !== 1) {
                    return false;
                }
                // Ajustado para 2 ou mais paradas
                if (paradasSelecionadas === '2+ Paradas' && voo.numParadas < 2) {
                    return false;
                }
                return true; // Se passou por todos os filtros, inclui o voo
            });

            renderizarVoos(voosFiltrados);
        }

        // Event Listeners para os filtros
        if (filtroPrecoInput && valorPrecoSpan) {
            filtroPrecoInput.addEventListener('input', () => {
                valorPrecoSpan.textContent = `R$ ${filtroPrecoInput.value}`;
            });
        }

        if (aplicarFiltrosBtn) {
            aplicarFiltrosBtn.addEventListener('click', aplicarFiltros);
        }
        if (limparFiltrosBtn) {
            limparFiltrosBtn.addEventListener('click', () => {
                // Reseta os valores dos filtros para os padrões
                if (filtroPrecoInput && valorPrecoSpan) {
                    filtroPrecoInput.value = filtroPrecoInput.max;
                    valorPrecoSpan.textContent = `R$ ${filtroPrecoInput.max}`;
                }
                if (filtroCompanhiaSelect) {
                    filtroCompanhiaSelect.value = 'Todas';
                }
                if (filtroParadasSelect) {
                    filtroParadasSelect.value = 'Qualquer';
                }
                renderizarVoos(voosDisponiveis); // Mostra todos os voos novamente
            });
        }

        // Renderiza os voos iniciais ao carregar a página de resultados
        renderizarVoos(voosDisponiveis);
        // Garante que o valor inicial do slider seja exibido corretamente ao carregar
        if (filtroPrecoInput && valorPrecoSpan) {
            valorPrecoSpan.textContent = `R$ ${filtroPrecoInput.value}`;
        }
    }
});