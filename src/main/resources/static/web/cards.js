const { createApp } = Vue

createApp({
  data() {
    return {
      cliente:[],
      id: new URLSearchParams(location.search).get("id"), 
      card:[],
      clase:"",
      color:"",
      destiny:"",
      popup:"",
      cardDebit:[],
      credit:[],
      fecha:"",
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
        this.cliente=response.data
        this.card=this.cliente.cards.sort(this.order)
        this.FiltoCredito();
        this.FiltoDebito(); 
        this.checkDate();
        console.log(this.fecha);
      })
    },
    checkDate(cliente){
      this.fecha=cliente.thruDate-cliente.fromDate
    },

    logOut(){
      axios.post('/api/logout').then(()=>window.location.href="/web/index.html")
     },
    crearCards(){
      
      axios.post('/api/clients/current/cards',`cardType=${this.clase}&cardColor=${this.color}`);
    },
    FiltoDebito(){
        this.cardDebit=this.card.filter(tarj => tarj.type === "DEBIT")
    },
    FiltoCredito(){
        this.credit=this.card.filter(tarj => tarj.type === "CREDIT")
    },
    DeleteCard(id){
      axios.patch('/api/clients/currents/cards',`id=${id}`).then(()=>location.reload())
    }
  }
}).mount('#app')