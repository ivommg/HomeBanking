const { createApp } = Vue;

createApp({
  data() {
    return {
      loans: [],
      loanSelected: "",
      loanAuthenticated: [],
      payments: [],
      destinationAccount: "",
      accounts: [],
      contenedor:[],
      inputGuille:"",
      request: {
        id: [],
        amount:[],
        payment: [],
        accountDestinit: [],
      },
    };
  },
  created() {
    this.loadData("/api/loans");
    this.currentData("/api/clients/current");
  },
  methods: {
    loadData(url) {
      axios.get(url).then((response) => {
        this.loans = response.data;
        this.request.amount=this.amounts;
      });
    },
    currentData(url) {
      axios.get(url).then((response) => {
        this.accounts = response.data.accounts;
      });
    },

    PaymentSelect() {
      if (this.loanSelected != "") {
        this.loanAuthenticated = this.loans.find(
          (loan) => loan.name == this.loanSelected
        );
        this.payments = [];
        this.payments = this.loanAuthenticated.payment;
        this.request.id= this.loanAuthenticated.id;
        return this.payments;
      }
    },

    CalcPayments(propiert){
      this.contenedor = (this.request.amount/this.request.payment)* propiert;
      return this.contenedor.toFixed(2);
    },

    postear(){
        axios.post("/api/loans",{"id":this.request.id,"amount":this.request.amount,"payment":this.request.payment,"accountDestinit":this.request.accountDestinit}).then(()=>{
          Swal.fire('Loan Aproved', '', 'success')
          setTimeout(()=>window.location.assign("account.html"),"3000")
          
        }).catch(error=>Swal.fire(error.response.data, '', 'error'))
    }
  }, 
}).mount("#app");
