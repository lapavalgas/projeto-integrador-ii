<template>
    <section class="contato container fadeInDown" data-anime="1200">
        <div class="contato_sub_cadastro"> 
            <br/><p>SUAS COMPRAS:</p>
        </div><br/>
		<form id="form_compras" method="POST" action="./enviar.php" class="contato_form grid-8 formphp">
			<ul>
			<li>Serviço 1 - Profissional 1 - Valor: R$ 200,00</li>
			<li>Serviço 2 - Profissional 2 - Valor: R$ 80,00</li>
			<li>Serviço 3 - Profissional 3 - Valor: R$ 50,00</li>
            <li><br/></li>
            <li><br/></li>
            <h5>TOTAL: R$ 330,00</h5>
		</ul>

		<div class="contato_sub_cadastro grid-8">
			<form id="form_finalizar_compra" method="POST" action="./enviar.php" class="contato_form grid-8 formphp">
				
				<p>FINALIZAR COMPRA:</p>
				<br/>
				<label for="ncartao">Número do Cartão</label>
				<input type="text" id="ncartao" name="ncartao" required>

				<label for="ncartao">Nome no Cartão</label>
				<input type="text" id="nomecartao" name="nomecartao" required>

				<label for="cpfcartao">CPF</label>
				<input type="text" id="cpfcartao" name="cpfcartao" required>

				<label for="vcartao">Validade do Cartão</label>
				<input type="text" id="vcartao" name="vcartao" required>

				<label for="cscartao">Codigo de Segurança do Cartão</label>
				<input type="text" id="cscartao" name="cscartao" required>
				
				<label class="nao-aparece">Se você não é um robô, deixe em branco.</label>
				<input type="text" class="nao-aparece" name="leaveblank">
				<label class="nao-aparece">Se você não é um robô, não mude este campo.</label>
				<input type="text" class="nao-aparece" name="dontchange" value="http://">
				<br/><br/>

                <a href="" class="btn btn-preto">Finalizar</a>
            </form>
		</div>
	</section>
</template>

<script>
</script>

<style scoped>
</style>