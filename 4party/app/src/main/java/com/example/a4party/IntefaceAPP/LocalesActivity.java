package com.example.a4party.IntefaceAPP;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.LogInOutBusiness.LogInBusiness;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import java.io.File;


public class LocalesActivity extends AppCompatActivity {
    private TextView textViewTitulo;
    private Button botonOferta, scanner, botonImagen, botonCerrarSesion;
    private ImageView imageView;
    private CRUD crud;
    private Uri urimage;
    private static final int IMG = 1;
    private String emailuser;
    private FirebaseUser user;
    private FirebaseFirestore db;
    boolean press=false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_empresario);
        user = FirebaseAuth.getInstance().getCurrentUser();
        emailuser = user.getEmail();
        crud = new CRUD(this);
        textViewTitulo = findViewById(R.id.textView4);
        crud.ReadNameEstablecimiento(emailuser, textViewTitulo);
        botonOferta = findViewById(R.id.btncrearoferta);
        imageView = findViewById(R.id.vistalocalimg);
        botonImagen = findViewById(R.id.addimagenlocal);
        botonCerrarSesion = findViewById(R.id.logoutprofileempresa);
        scanner = findViewById(R.id.scanqr);
        LogOut();
        //EliminarCuenta(emailuser, user);
        addImg();
        crearOfertas();
        /*CARGAR IMAGEN LOCAL*/
        db = FirebaseFirestore.getInstance();
        db.collection("Empresarios").document(emailuser).get().addOnSuccessListener(documentSnapshot -> {
            String imgen = documentSnapshot.getString("urlimagen");
            Picasso.get().load(imgen).fit().transform(new RoundedTransformation(50, 10)).centerCrop().into(imageView);
        });
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press = true;
                IntentIntegrator integrator = new IntentIntegrator(LocalesActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Lector de QRs");
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);

                // Establecer la orientación vertical
                integrator.setOrientationLocked(true);
                integrator.initiateScan();
            }
        });

    }


    private void LogOut() {
        botonCerrarSesion.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, LogInBusiness.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private void addImg() {
        botonImagen.setOnClickListener(view -> {
            intentImg();
        });
    }

    private void intentImg() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG && resultCode == RESULT_OK) {
            Toast.makeText(this, "SUBIENDO...", Toast.LENGTH_SHORT).show();
            urimage = data.getData();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("img").child(emailuser);
            storageRef.putFile(urimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Aqui lo meto en firestore
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String emailuser = user.getEmail();
                            crud.updateImage(url, emailuser);
                            Picasso.get().load(uri).fit().into(imageView);
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(LocalesActivity.this, "ERROR REINTENTALO MAS TARDE", Toast.LENGTH_SHORT).show();
                    });
                }
            });
        }

        if (result!=null){
            if(result.getContents()==null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MostrarValores.class);
                intent.putExtra("valorqr",result.getContents());
                startActivity(intent);
                finish();
            }

        }
    }

    private void crearOfertas() {
        botonOferta.setOnClickListener(view -> {
            Intent intent = new Intent(LocalesActivity.this, CrearOfeta.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

}

class RoundedTransformation implements com.squareup.picasso.Transformation {
    private final int radius;
    private final int margin;

    public RoundedTransformation(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }


    @Override
    public Bitmap transform(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin,
                source.getHeight() - margin), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }
        return output;
    }

    @Override
    public String key() {
        return "rounded(r=" + radius + ", m=" + margin + ")";
    }
}