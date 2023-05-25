package com.example.a4party.IntefaceAPP;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    private EditText editNombre;
    private EditText editApellidos;
    private EditText editDni;
    TextView mail,name;

    // Obtén el correo electrónico del usuario actual
    String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button backButton = findViewById(R.id.backbutton);
        Button uploadButton = findViewById(R.id.designbutton);
        editNombre = findViewById(R.id.editnamevalue);
        editApellidos = findViewById(R.id.editlastnamevalue);
        editDni = findViewById(R.id.editdnivalue);
        name= findViewById(R.id.editprofilename);
        mail= findViewById(R.id.editprofilemail);

        // Obtén los extras del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre");
            String email = extras.getString("email");

            // Establece los valores en los EditText correspondientes
            name.setText(nombre);
            mail.setText(userEmail);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Obtén los nuevos valores de los campos de edición
                String nuevoNombre = editNombre.getText().toString().trim();
                String nuevoApellidos = editApellidos.getText().toString().trim();
                String nuevoDni = editDni.getText().toString().trim();

                // Actualiza los datos en Firestore y en la autenticación de Firebase
                actualizarDatos(userEmail, nuevoNombre, nuevoApellidos, nuevoDni);
            }
        });

    }

    private void actualizarDatos(String userEmail, String nombre, String apellidos, String dni) {
        // Verifica si alguno de los campos está vacío
        if (nombre.isEmpty() && apellidos.isEmpty() && dni.isEmpty()) {
            Toast.makeText(EditProfile.this, "No se han realizado cambios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtén una referencia a la colección "usuarios" en Firestore
        CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("Usuarios");

        // Crea una consulta para obtener el documento del usuario actual según su correo electrónico
        Query query = usersCollection.whereEqualTo("email", userEmail);

        // Realiza la consulta a Firestore
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // La consulta se completó exitosamente
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                        // Obtiene el primer documento (suponiendo que solo hay uno por usuario)
                        DocumentSnapshot document = querySnapshot.getDocuments().get(0);

                        // Obtén la referencia del usuario actual en Firestore
                        String userId = document.getId();
                        DocumentReference userRef = usersCollection.document(userId);

                        // Crea un mapa para almacenar los campos a actualizar en Firestore
                        Map<String, Object> updates = new HashMap<>();

                        // Verifica cada campo y agrega el valor correspondiente al mapa de actualización
                        if (!nombre.isEmpty()) {
                            updates.put("nombre", nombre);
                        }
                        if (!apellidos.isEmpty()) {
                            updates.put("apellido", apellidos);
                        }
                        if (!dni.isEmpty()) {
                            updates.put("dni", dni);
                        }

                        if (updates.isEmpty()) {
                            // No se han realizado cambios en los campos
                            Toast.makeText(EditProfile.this, "No se han realizado cambios", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Actualiza los campos correspondientes en el documento del usuario
                        userRef.update(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Actualización exitosa en Firestore
                                        Toast.makeText(EditProfile.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Error al actualizar en Firestore
                                        Toast.makeText(EditProfile.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        // Actualiza el nombre en la autenticación de Firebase
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest.Builder profileUpdatesBuilder = new UserProfileChangeRequest.Builder();
                        if (!nombre.isEmpty()) {
                            profileUpdatesBuilder.setDisplayName(nombre);
                        }
                        UserProfileChangeRequest profileUpdates = profileUpdatesBuilder.build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Actualización exitosa en la autenticación de Firebase
                                            Toast.makeText(EditProfile.this, "Actualización exitosa en la autenticación de Firebase", Toast.LENGTH_SHORT).show();
                                            goBack();
                                        } else {
                                            // Error al actualizar en la autenticación de Firebase
                                            Toast.makeText(EditProfile.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                } else {
                    // Error al realizar la consulta a Firestore
                    Log.e("EditProfile", "Error al obtener los datos del perfil: " + task.getException());
                }
            }
        });
    }

    private void goBack() {
        Intent intent = new Intent(EditProfile.this, HOME_ACTIVITY.class);
        intent.putExtra("fragment", "profile"); // Envía un identificador del fragmento "carrito"
        startActivity(intent);
    }
}