package com.example.projectbwa.Sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectbwa.home.HomeActivity
import com.example.projectbwa.R
import com.example.projectbwa.Sign.signup.SignUpActivity
import com.example.projectbwa.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    //declarite username dan password sbg string
    lateinit var iUsername:String
    lateinit var iPassword:String

    //inisialisasi database
    lateinit var mDatabase : DatabaseReference
    lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //value database
        mDatabase = FirebaseDatabase.getInstance().getReference("User") //"User merupakan table database
        preferences = Preferences(this)

        //onboarding tidak akan ditampilkan lagi jika sudah berhasil login, langsung ditampilkan ke home
        preferences.setValues("onboarding", "1")
        if(preferences.getValues("status").equals("1")){
            finishAffinity()
            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btn_daftar.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_masuk.setOnClickListener {
            // untuk menampung inputan dari SignIn.xml
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            //konfirmasi jika username atau password tidak diisi akan menampilkan error
            if(iUsername.equals("")){
                et_username.error = "Silahkan masukkan username anda"
                et_username.requestFocus()
            } else if(iPassword.equals("")) {
                et_password.error = "Silahkan masukkan password anda"
                et_password.requestFocus()
            } else {
                //user name dan password dari signin.xml dibawa ke pushlogin
                pushLogin(iUsername, iPassword)
            }
        }
    }
    //mengambil data yang dikirim dari "pushLogin"
    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError : DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot : DataSnapshot) {
                //diarahkan ke class user
                var user = dataSnapshot.getValue(User::class.java)//class merupakan sebuah model untuk menangani data dari table user seperti nama, saldo, password, dsb
                //jika user tidak ada di database maka akan menampilkan error
                if(user == null) {
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan", Toast.LENGTH_LONG).show()
                //jika didatabase "User" terdapat username
                } else{
                    //jika password sesuai dengan username maka akan lanjut ke tampilan home

                    if(user.password.equals(iPassword)){
                        //untuk menampung jika berhasil login
                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("username", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("saldo", user.saldo.toString())
                        preferences.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@SignInActivity, "Password anda salah", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}