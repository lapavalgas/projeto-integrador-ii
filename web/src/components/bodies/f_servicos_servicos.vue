<template>
  <section>
    <section class="contato container fadeInDown" data-anime="1200">
      <div class="contato_sub_servicos">
        <p
          style="
            font-family: arial;
            font-size: 30px;
            line-height: 25px;
            text-transform: uppercase;
            color: #195e83;
            font-weight: bold;
            border-top: 5px;
            border-bottom: 0px;
          "
        >
          serviços oferecidos por nossos usuários
        </p>
      </div>
      <br />
    </section>
    <f_servicos_cards_line
      v-for="line in marketplaceByLine"
      v-bind:key="line.servico_id"
      v-bind:line="line"
    >
    </f_servicos_cards_line>
  </section>
</template>

<script>
import f_servicos_cards_line from "@/components/bodies/servicos/f_servicos_cards_line.vue";
export default {
  name: "f_servicos_servicos",
  props: {
    msg: String,
  },
  components: {
    f_servicos_cards_line,
  },
  data() {
    return {
      fastservice_url: "http://localhost:8080",
      marketplace: [],
      marketplaceByLine: [],
    };
  },
  beforeMount: async function () {
    let responseMarketplace = await this.readMarketplace();
    responseMarketplace.forEach((element) => {
      if (element.statusOperante == "true") {
        element.card_id = "card_" + element.servico_id;
        this.marketplace.push(element);
      }
    });
    this.marketplaceByLine = this.chunk(this.marketplace, 3);
  },
  methods: {
    readMarketplace: async function () {
      return fetch(this.fastservice_url + "/usuarios/0/servicos/marketplace", {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "GET",
      }).then((response) => response.json());
    },

    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },
    chunk: function (arr, len) {
      var chunks = [],
        i = 0,
        n = arr.length;

      while (i < n) {
        chunks.push(arr.slice(i, (i += len)));
      }
      return chunks;
    },
  },
};
</script>

<style scoped>
</style>