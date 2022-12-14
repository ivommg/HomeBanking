const app = Vue.createApp({
  data() {
    return {
      info: null,
      urlApi: "/api/clients/current",
      clientes: [],
      cuentas: [],
      loans: [],
      card: [],
    };
  },
  created() {
    this.cargarData(this.urlApi);
  },
  methods: {
    order(a, b) {
      return a.id - b.id;
    },
    cargarData(url) {
      axios.get(url).then((response) => {
        response;
        this.info = response;
        this.clientes = this.info.data;
        this.loans = this.info.data.loans.sort(this.order);
        this.card = this.info.data.cards.sort(this.order);
      });
    },
    logOut() {
      axios.post("/api/logout")
        .then(() => {Swal.fire('Loan Aproved', '', 'success')
        setTimeout(()=>window.location.assign("index.html"),"1500")});
    },
    createAccount(){
      axios.post("/api/clients/current/accounts")
    }
  },
});
app.mount("#app");
//deleted and put
