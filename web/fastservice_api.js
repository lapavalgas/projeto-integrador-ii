var fastservice_url = 'http://localhost:8080';

async function cadastraUsuarios(form_usuarioDto) {
    // form
    // {
    //     "cpf": "00000000",
    //     "nome": "Usuario teste 0",
    //     "dataNascimento": "01/01/1990",
    //     "genero": "H",
    //     "email": "teste@teste.com",
    //     "telefone": "000000000",
    //     "cep": "88000-000",
    //     "estado": "Estado Teste",
    //     "municipio":"Municipio Teste",
    //     "bairro":"Bairro Teste",
    //     "numero": "00",
    //     "complemento": "AP. 000"
    // }
    let response = await fetch(fastservice_url + '/usuarios', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(form_usuarioDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function signIn(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/login/' + usuario_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function readUsuarios(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function deleteUsuarios(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'DELETE',
    });
    let responseData = await response.json();
    return responseData;
}

async function readUsuariosContatos(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/contatos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function updateUsuariosContatos(usuario_id, form_contatoDto) {
    // form
    // {
    //     "email": "teste-atualizado@teste.com",
    //     "telefone": "000000001"
    // }
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/contatos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(form_contatoDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function readUsuariosEnderecos(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/enderecos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function updateUsuariosEnderecos(usuario_id, form_enderecoDto) {
    // form
    // {
    //     "cpf": "11111-111",
    //     "cep": "88101-300",
    //     "estado": "Estado Teste ALTERADO",
    //     "municipio":"Municipio Teste ALTERADO",
    //     "bairro":"Bairro Teste ALTERADO",
    //     "numero": "11",
    //     "complemento": "AP. 111"
    // }
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/enderecos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(form_enderecoDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function cadastraServicos(usuario_id, form_servicoDto) {
    // form
    // {
    //     "nome": "Programação Java",
    //     "descricao": "Desenvolvimento de pequenos sistemas transacionais em Java.",
    //     "categoria": "Informática",
    //     "valor": "1200.00",
    //     "statusOperante": true,
    //     "usuarioProfissional": {
    //         "usuario_id": 1,
    //         "nome": "!!!!!!!!!!!!!!!"
    //     }
    // }
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(form_servicoDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function readMarketplace(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos/marketplace', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}


async function readUsuariosServicosAll(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function readUsuariosServicos(usuario_id, servico_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos/' + servico_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function deleteUsuariosServicos(usuario_id, servico_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos/' + servico_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'DEL',
    });
    let responseData = await response.json();
    return responseData;
}

async function cadastraPedidos(usuario_id, servico_id, form_pedidoDto) {
    // form
    // {
    //     "formaDePagamento": "PIX"
    // }
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/servicos/' + servico_id + '/pedidos', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(form_pedidoDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function readPedidos(usuario_id, pedido_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/' + pedido_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function readPedidosCliente(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/cliente', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function readPedidosProfissional(usuario_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/profissional', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}

async function updateProfissionalPedidosDisponibilidades(usuario_id, pedido_id, form_disponibilidadeDto) {
    // form
    // {
    //     "disponibilidade": [
    //         {
    //             "dataHora": "2021-04-03"
    //         },
    //         {
    //             "dataHora": "2021-04-03"
    //         },
    //         {
    //             "dataHora": "2021-04-05"
    //         }
    //     ]
    // }
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/' + pedido_id + '/disponibilidades', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(form_disponibilidadeDto)
    });
    let responseData = await response.json();
    return responseData;
}

async function updateClientePedidosDisponibilidades(usuario_id, pedido_id, disponibilidade_id) {
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/' + pedido_id + '/disponibilidades/' + disponibilidade_id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
    });
    let responseData = await response.json();
    return responseData;
}

async function updatePedidosFinalizar(usuario_id, pedido_id) {
    // form
    // fixed to true
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/' + pedido_id + '/finalizar?stts=true', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
    });
    let responseData = await response.json();
    return responseData;
}

async function updateClientePedidosAvaliar(usuario_id, pedido_id, notaAvaliacao) {
    // form
    // 0 to 5
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/' + pedido_id + '/avaliar?stts=' + notaAvaliacao, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'PUT',
    });
    let responseData = await response.json();
    return responseData;
}

async function readPedidosByFilter(usuario_id, tipoServico) {
    // form
    // true or false
    let response = await fetch(fastservice_url + '/usuarios/' + usuario_id + '/pedidos/filtrar?servicofinalizado=' + tipoServico, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'GET',
    });
    let responseData = await response.json();
    return responseData;
}