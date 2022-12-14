const app = Vue.createApp({   
    data() {
      return {
        info:null,
        urlApi:"http://localhost:8080/api/clients",
      }
    },
    created(){
        this.cargarData(this.urlApi);
    },
    methods:{
        cargarData(url){
            axios.get(url).then(response => {response
            this.info=response;
                
                
            })
        },
        

    },
    computed:{

    },
  })
app.mount('#app')
//deleted and put