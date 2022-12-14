const { createApp } = Vue

createApp({
  data() {
    return {
      cuentas:[],
      cuenta:[],
      transactions:"",
      id:new URLSearchParams(location.search).get("id"),
    }
  },
  created(){
    this.loadData("/api/clients/current")
  },
  methods:{
    order(a, b) {
      return a.id - b.id
  },
    loadData(url){
      axios.get(url).then(response =>{
        this.cuentas=response.data;
        this.cuenta=this.cuentas.accounts.find(cuenta => cuenta.id==this.id);
        this.transactions=this.cuenta.transactions.sort(this.order);

      })
    },
    logOut(){
      axios.post('/api/logout').then(()=>window.location.href="/web/index.html")
     },
  }
}).mount('#app')