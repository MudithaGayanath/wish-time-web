import {signIn} from "../service/userService.js"

let token ;

async function testSignIn(){
    const signInData = {
        username:"Test_User_1",
        password:"123456"
    }
    const  rs = await  signIn(signInData);
    if(!rs.status){
        if(rs.data.username){
            console.log(rs.data.username)
        }
        if(rs.data.password){
            console.log(rs.data.password)
        }
        if(rs.data.credentials){
            console.log(rs.data.credentials)
        }
    }else{
        console.log("Sign In Successful")
        token = rs.data.token;
        console.log(rs.data);
    }
}

testSignIn();
