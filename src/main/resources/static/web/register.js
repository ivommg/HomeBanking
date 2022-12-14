const app = Vue.createApp({   
    data() {
      return {
        info:null,
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        emailIngresar:"",
        passwordIngresar:"",
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
            axios.post('/api/login',`email=${this.emailIngresar}&password=${this.passwordIngresar}`).then(()=>window.location.pathname="/web/account.html")
        },
        registrar(){
            axios.post('/api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)
            .then(() => {
                this.emailIngresar=this.email
                this.passwordIngresar=this.password
                this.ingresar()
            })
        },

          
        
    },
    computed:{

    },
  })
app.mount('#app')