// Função para obter o valor de um cookie pelo nome
function getCookie(nome) {
    let cookies = document.cookie.split("; ");
    for (let i = 0; i < cookies.length; i++) {
        let [chave, valor] = cookies[i].split("=");
        if (chave === nome) {
            return valor;
        }
    }
    return null;
}

document.addEventListener("DOMContentLoaded", function () {
    let tipoUsuario = getCookie("tipoUsuario");  // Obtém o tipo do usuário do cookie

    if (tipoUsuario === "paciente") {
        document.querySelectorAll(".paciente-only").forEach(el => el.style.display = "block");
        document.querySelectorAll(".medico-only").forEach(el => el.style.display = "none");
    } else if (tipoUsuario === "medico") {
        document.querySelectorAll(".medico-only").forEach(el => el.style.display = "block");
        document.querySelectorAll(".paciente-only").forEach(el => el.style.display = "none");
    } else {
        document.querySelectorAll(".paciente-only, .medico-only").forEach(el => el.style.display = "none");
    }
});
