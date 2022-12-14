const { createApp } = Vue;

createApp({
  data() {
    return {
      info: null,
      cuentas: [],
      accountOrigin: "",
      cuentaDestino: "",
      amount: "",
      description: "",
      destiny: "",
      destinationAccount:[],

    };
  },
  created() {
    this.loadData("/api/clients/current");
  },
  methods: {
    loadData(url) {
      axios.get(url).then((response) => {
        this.info = response;
        this.cuentas = this.info.data.accounts;
      });
    },
    logOut() {
      axios
        .post("/api/logout")
        .then(() => (window.location.href = "/web/index.html"));
    },
    transferencia(){
      axios.post(
          `/api/transactions?amount=${this.amount}&descriptions=${this.description}&accountOrigin=${this.accountOrigin}&accountDestiny=${this.cuentaDestino}`
        )
        .then(() => {
          Swal.fire("congratulation", "success"),
            setTimeout(() => {
              window.location.pathname = "/web/account.html";
            }, "3000");
        });
    },
    alerta() {
      Swal.fire({
        title: "Are you sure you want to transfer?",
        text: "Check that the data are correct",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, transfer!",
      }).then((result) => {
        if (result.isConfirmed) {
          this.transferencia();
        }
      });
    },
  },
  computed:{
    destinationAccounts(){

        this.destinationAccount = this.cuentas.filter(account => account.number != this.accountOrigin)

    }
  },
}).mount("#app");
