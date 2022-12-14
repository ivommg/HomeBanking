const app = Vue.createApp({   
    data() {
      return {
        info:null,
        email:[],
        password:[],
      }
    },
    created(){
        this.cargarData("/api/clients");
    },
    methods:{
        cargarData(url){
            axios.get(url).then(response => {response
            this.info=response;
            })
        },
        ingresar(){
          axios.post('/api/login',`email=${this.email}&password=${this.password}`).then(()=>window.location.href="/web/account.html")
        }
    },
    computed:{

    },
  })
app.mount('#app')