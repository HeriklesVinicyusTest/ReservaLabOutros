function validacao() {
    if(document.form.cpf.value===""){
        alert("O campo cpf é obrigatório!");
        document.form.cpf.focus();
        return false;
    }
}


