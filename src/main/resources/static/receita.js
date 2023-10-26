const img = document.getElementById("imagem");
const title = document.getElementById("title");
const receitaEl = document.getElementById("receita-paragrafo");
const deleteButton = document.getElementById("delete-btn");

import { BACKEND_ENDPOINT } from "./consts.js";

deleteButton.addEventListener("click", (ev)=>{
    const id = getIdFromLink(window.location.href)
    fetch(BACKEND_ENDPOINT + "/api/deletar/" + id, {method: "DELETE"})
        .then(res=>{
            window.location.href = "/"
        });
});

function getIdFromLink(link) {
    const partesDoLink = link.split('/');
    const partesNaoVazias = partesDoLink.filter(part => part.trim() !== '');
    const ultimoParametro = partesNaoVazias.pop();
    return ultimoParametro;
}

function redirectToNotFound(){
    window.location.href = "/not-found.html"
}

function initializePage(nomeReceita, quantPessoas, receita, imagem){
    const span = document.createElement("span");

    span.textContent = "Serve " + quantPessoas + " pessoas";
    title.textContent = nomeReceita;

    title.appendChild(span);

    receitaEl.textContent = receita;
    img.setAttribute("src", "/images/" + imagem);
}

function fetchInformations(){
    const id = getIdFromLink(window.location.href)
    fetch(BACKEND_ENDPOINT + "/api/receita/" + id)
        .then(res=>{
            if(res.ok){
                return res.json();
            }else{
                redirectToNotFound();
            }
        })
        .then(json => {
            const { imagem, nome, quantiaPessoas, receita } = json;
            initializePage(nome, quantiaPessoas, receita, imagem);
        });
}

fetchInformations();