// Script de Diario
// Script do Dropdown Menu
const dropdownButton = document.getElementById('dropdownButton');
const dropdownItems = document.querySelectorAll('.dropdown-item[data-value]');

// Adiciona eventos de clique nos itens do dropdown
dropdownItems.forEach(item => {
    item.addEventListener('click', function (event) {
        event.preventDefault(); // Evita recarregar a página
        const selectedValue = this.getAttribute('data-value'); // Obtém o valor selecionado
        dropdownButton.textContent = selectedValue; // Atualiza o texto do botão
        localStorage.setItem('selectedMood', selectedValue); // Armazena a seleção no localStorage
    });
});

const savedMood = localStorage.getItem('selectedMood');
if (savedMood) {
    dropdownButton.textContent = savedMood;
}

document.getElementById('btnSalvar').addEventListener('click', function () {
    alert('Registro salvo com sucesso!');
});

const menuLinks = document.querySelectorAll('.menu a');
menuLinks.forEach(link => {
    link.addEventListener('click', function (event) {
        if (this.getAttribute('href') === '#') {
            event.preventDefault();
        }
    });
});


// Script de Login
document.getElementById("btnPaciente").addEventListener("click", function () {
    document.getElementById("loginForm").classList.add("active");
    document.getElementById("signupPaciente").classList.remove("active");
    document.getElementById("signupMedico").classList.remove("active");
});

document.getElementById("btnMedico").addEventListener("click", function () {
    document.getElementById("loginForm").classList.add("active");
    document.getElementById("signupPaciente").classList.remove("active");
    document.getElementById("signupMedico").classList.remove("active");
});

// Alterna para o formulário de cadastro de Paciente
document.getElementById("signupLink").addEventListener("click", function () {
    document.getElementById("loginForm").classList.remove("active");
    document.getElementById("signupPaciente").classList.add("active");
    document.getElementById("signupMedico").classList.remove("active");
});

// Alterna para o formulário de cadastro de Médico
document.getElementById("btnMedico").addEventListener("click", function () {
    document.getElementById("loginForm").classList.remove("active");
    document.getElementById("signupMedico").classList.add("active");
    document.getElementById("signupPaciente").classList.remove("active");
});

// Voltar para o Login a partir do Cadastro de Paciente
document.getElementById("loginLinkPaciente").addEventListener("click", function () {
    document.getElementById("signupPaciente").classList.remove("active");
    document.getElementById("loginForm").classList.add("active");
});

// Voltar para o Login a partir do Cadastro de Médico
document.getElementById("loginLinkMedico").addEventListener("click", function () {
    document.getElementById("signupMedico").classList.remove("active");
    document.getElementById("loginForm").classList.add("active");
});


// Script de Progresso
let counts = JSON.parse(localStorage.getItem('moodCounts')) || {
    Feliz: 0,
    Animado: 0,
    Tranquilo: 0,
    Neutro: 0,
    Nervoso: 0,
    Triste: 0,
    Ansioso: 0
};

const updateEmotionDisplay = () => {
    const total = Object.values(counts).reduce((a, b) => a + b, 0);

    Object.keys(counts).forEach(key => {
        const percentage = (total === 0) ? 0 : ((counts[key] / total) * 100).toFixed(2);
        document.getElementById(`count${key}`).innerText = `${counts[key]} vezes`;
        const progressBar = document.getElementById(`progress${key}`).querySelector('.progress-bar');
        progressBar.style.width = `${percentage}%`;
        progressBar.innerText = `${key} (${counts[key]} vezes)`;
    });
};

// Atualiza os displays ao carregar a página
updateEmotionDisplay();

// Registra a seleção de humor no diário
const selectedMood = localStorage.getItem('selectedMood');
if (selectedMood) {
    counts[selectedMood]++;
    localStorage.setItem('moodCounts', JSON.stringify(counts));
}

// Script de Meus Registros
const apiUrl = 'https://sua-api.com/registros'; // Substitua pelo endpoint correto

async function carregarRegistros() {
    try {
        const resposta = await fetch(apiUrl);
        const registros = await resposta.json();

        const lista = document.getElementById('registros-lista');
        lista.innerHTML = '';

        if (registros.length === 0) {
            lista.innerHTML = '<p class="text-center">Nenhum registro encontrado.</p>';
            return;
        }

        registros.forEach(registro => {
            const item = document.createElement('div');
            item.classList.add('registro-item');
            item.innerHTML = `
                <div class="registro-header">
                    <span class="registro-data">${registro.data}</span>
                    <span class="registro-humor">${registro.humor}</span>
                </div>
                <p class="registro-texto">${registro.texto}</p>
            `;
            lista.appendChild(item);
        });
    } catch (error) {
        console.error('Erro ao carregar registros:', error);
        const lista = document.getElementById('registros-lista');
        lista.innerHTML = '<p class="text-center text-danger">Erro ao carregar os registros.</p>';
    }
}

document.addEventListener('DOMContentLoaded', carregarRegistros);

// Script de Registros de Pacientes
const pacientes = {
    "12345678900": [
        { data: "2024-12-01", humor: "Feliz", texto: "Dia incrível!" },
        { data: "2024-12-02", humor: "Ansioso", texto: "Preparando-me para um evento importante." }
    ],
    "98765432100": [
        { data: "2024-12-03", humor: "Triste", texto: "Dia complicado, mas superei." }
    ]
};

const form = document.getElementById('cpfForm');
const cpfInput = document.getElementById('cpfInput');
const resultadoDiv = document.getElementById('resultado');
const registrosList = document.getElementById('registros');
const erroDiv = document.getElementById('erro');

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const cpf = cpfInput.value.replace(/\D/g, '');

    registrosList.innerHTML = '';
    resultadoDiv.style.display = 'none';
    erroDiv.style.display = 'none';

    if (pacientes[cpf]) {
        const registros = pacientes[cpf];
        registros.forEach(registro => {
            const listItem = document.createElement('li');
            listItem.classList.add('list-group-item');
            listItem.innerHTML = `<strong>Data:</strong> ${registro.data} | <strong>Humor:</strong> ${registro.humor}<br>
                                  <strong>Texto:</strong> ${registro.texto}`;
            registrosList.appendChild(listItem);
        });
        resultadoDiv.style.display = 'block';
    } else {
        erroDiv.style.display = 'block';
    }
});
