const form = document.getElementById("form");

import { BACKEND_ENDPOINT } from "./consts.js";

form.addEventListener('submit', (ev)=>{
    ev.preventDefault();

    const formData = new FormData(form)

    const uploadData = new FormData();
    uploadData.append("file", formData.get("file"));

    fetch(BACKEND_ENDPOINT + "/api/upload", {
        method: "POST",
        body: uploadData
      }).then(res => res.json()).then(response => {
          if (response.status !== undefined && response.status === "success") {
            return response;
          } else {
            alert("Erro ao fazer upload do arquivo.");
            return undefined;
          }
        })
        .then(data => {
            if(data && data.result){
                formData.delete("file");
                formData.append("imagem", data.result);
                cadastrarReceita(formData);
            }
        });
      
})

function cadastrarReceita(formData){
    const receita = {
        "nome": formData.get("nome"),
        "receita": formData.get("receita"),
        "quantiaPessoas": formData.get("quantiaPessoas"),
        "imagem": formData.get("imagem"),
    }

    console.log(receita);

    fetch(BACKEND_ENDPOINT + "/api/cadastrar", { method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(receita) })
        .then(res=>{
            if(res.ok){
                window.location.href = "/";
            }else{
                alert("Erro ao cadastrar no formulário. Mais informações no console")
                console.log(res);
                console.log(res.json);
            }
        });
}