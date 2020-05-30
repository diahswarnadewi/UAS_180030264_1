package com.bh183.diahswarnadewi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_film";
    private final static String TABLE_FILM ="t_film";
    private final static String KEY_ID_FILM = "ID_Film";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_TGL = "Release_Date";
    private final static String KEY_COVER = "Cover";
    private final static String KEY_GENRE = "Genre";
    private final static String KEY_DURATION = "Duration";
    private final static String KEY_RATING = "Rating";
    private final static String KEY_SYNOPSIS = "Synopsis";
    private Context context;


    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILM = "CREATE TABLE " + TABLE_FILM
                + "(" + KEY_ID_FILM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_TGL + " DATE, "
                + KEY_COVER + " TEXT, " + KEY_GENRE + " TEXT, "
                + KEY_DURATION + " TEXT, " + KEY_RATING + " TEXT, " + KEY_SYNOPSIS + " TEXT);";

        db.execSQL(CREATE_TABLE_FILM);
        inisialisasiFilmAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_FILM;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahFilm(Film dataFilm){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TGL, dataFilm.getRilis());
        cv.put(KEY_COVER, dataFilm.getCover());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURATION, dataFilm.getDurasi());
        cv.put(KEY_DURATION, dataFilm.getDurasi());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_SYNOPSIS, dataFilm.getSynopsis());


        db.insert(TABLE_FILM, null, cv);
        db.close();
    }

    public void tambahFilm(Film dataFilm, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TGL, dataFilm.getRilis());
        cv.put(KEY_COVER, dataFilm.getCover());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURATION, dataFilm.getDurasi());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_SYNOPSIS, dataFilm.getSynopsis());
        db.insert(TABLE_FILM, null, cv);
    }

    public void editFilm(Film dataFilm){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_TGL, dataFilm.getRilis());
        cv.put(KEY_COVER, dataFilm.getCover());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURATION, dataFilm.getDurasi());
        cv.put(KEY_RATING, dataFilm.getRating());
        cv.put(KEY_SYNOPSIS, dataFilm.getSynopsis());

        db.update(TABLE_FILM, cv, KEY_ID_FILM + "=?", new String[]{String.valueOf(dataFilm.getIdFilm())});
        db.close();
    }

    public void hapusFilm (int idFilm){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FILM, KEY_ID_FILM + "=?", new String[]{String.valueOf(idFilm)});
        db.close();
    }

    public ArrayList<Film> getAllFilm(){
        ArrayList<Film> dataFilm = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FILM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do {
                Film tempFilm = new Film(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)

                );

                dataFilm.add(tempFilm);
            } while (csr.moveToNext());
        }

        return dataFilm;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiFilmAwal(SQLiteDatabase db){
        int idFilm = 0;

        // Menambahkan data film ke 1
        Film film1 = new Film(
                idFilm,
                "Aladdin",
                "24 Mei 2019",
                storeImageFile(R.drawable.film1),
                "Drama, ",
                "128 menit",
                "8,5 - IMDb version",
                "Aladdin, seorang pencuri muda yang baik hati (sering disebut \"tikus jalanan\") yang tinggal di kota Arab, Agrabah, bersama dengan monyet peliharaannya, Abu, menyelamatkan dan berteman dengan Putri Jasmine, yang menyelinap keluar dari istana untuk menjelajah, bosan dengan hidupnya yang terlindung. Sementara itu, wazir agung Jafar berencana untuk menggulingkan ayah Jasmine sebagai Sultan. Dia, bersama dengan sahabat burung kakatua peliharaannya Iago, mencari lampu ajaib yang tersembunyi di Gua Keajaiban yang akan memberinya tiga permintaan. Hanya satu orang yang layak untuk masuk: \"berlian dalam kesulitan\", yang dia putuskan adalah Aladdin. Aladdin ditangkap dan Jafar membujuknya untuk mengambil lampu. Di dalam gua, Aladdin menemukan karpet ajaib dan mendapatkan lampu. Dia memberikannya kepada Jafar, yang melipat gandakan salibnya dan melemparkannya kembali ke gua, meskipun Abu mencuri lampu kembali."
        );

        tambahFilm(film1, db);
        idFilm++;

        // Menambahkan data film ke 2
        Film film2 = new Film(
                idFilm,
                "Coco_(2017)",
                "10 Januari 2017 ",
                storeImageFile(R.drawable.film2),
                "Fantasi,Keluarga,Animasi",
                "2hours 2minutes",
                "8,4 - IMDb version",
                " Film COCO merupakan kisah Miguel Rivera seorang anak berusia 12 tahun yang tinggal bersama neneknya bernama Coco. Mereka tinggal di sebuah desa kecil di Meksiko. Saat Coco kecil, ia tinggal bersama sang ibu, Imelda Rivera, dan saat itu musik sangat dilarang dalam keluarganya. Namun kini sang cicit Miguel justru diam-diam bermimpi menjadi musisi seperti Ernesto de la Cruz yang merupakan seorang bintang film dan Suatu ketika Miguel menemukan sebuah foto yang ia ketahui sebagai Ernesto dan menyimpulkan bahwa dirinya adalah cicit Ernesto. Sejak saat itu, Miguel mencoba memasuki makam Ernesto dan mencuri gitarnya untuk digunakan dalam sebuah pertunjukan.Ajaibnya, ketika Miguel memetik gitar, ia menjadi tidak terlihat oleh semua orang di tempat pertunjukannya. Ia hanya bisa melihat dan dilihat oleh anjingnya, serta kerabatnya yang sudah meninggal saat itu yang sedang berkunjung dari Negeri Orang Mati untuk liburan, kemudian membawa serta Miguel ke alam mereka. "
        );

        tambahFilm(film2, db);
        idFilm++;

        // Menambahkan data film ke 3
        Film film3 = new Film(
                idFilm,
                "The Bos Baby",
                "03 April 2015",
                storeImageFile(R.drawable.film3),
                "Keluarga,Fantasi,Animasi",
                "2hours 17minutes",
                "7,2 - IMDb version",
                "Film The Boss Baby diadaptasi dari buku cerita bergambar karya Marla Frazee yang rilis di tahun 2010. Kisahnya berawal dari Tim Templeton (Miles Christopher Bakshi), bocah berusia 7 tahun yang merasa kehidupannya sangat sempurna. Sebagai anak tunggal, Tim sangat disayang oleh kedua orang tuanya, Ted (Jimmy Kimmel) dan Janice (Lisa Kudrow). Sayangnya kebahagiaan Tim tidak berlangsung lama karena datangnya anggota baru, bayi laki-laki yang sangat menggemaskan bernama Theodore alias Boss Baby (Alec Baldwin). Bencana pun mulai datang, Tim yang sudah terbiasa dimanja orang tuanya terpaksa harus berbagi kasih sayang dengan Boss Baby. Kehadiran Boss Baby pun membuat hari-hari Tim berubah 180 derajat.Pada suatu malam Tim menemukan fakta bahwa Boss Baby ternyata bukanlah bayi biasa. Selain berbicara layaknya orang dewasa, Boss Baby ternyata adalah seorang agen rahasia dari perusahaan bayi bernama Baby Corp. Baby Corp memiliki misi khusus untuk menjaga kelangsungan hidup bayi karena ancaman Puppy Co, sebuah perusahaan anak anjing. Di bawah pimpinan Francis E. Francis (Steve Buscemi), Puppy Co punya misi khusus untuk membuat manusia lebih mencintai puppies alias anak anjing ketimbang bayi. Tim dan Baby Boss pun akhirnya bekerja-sama untuk menggagalkan misi Francis."
        );

        tambahFilm(film3, db);
        idFilm++;

        // Menambahkan data film ke 4
        Film film4 = new Film(
                idFilm,
                "Wonder Park",
                "15 Maret 2019",
                storeImageFile(R.drawable.film4),
                "Keluarga,Fantasi,Animasi",
                "2hours 17minutes",
                "8,2 - IMDb version",
                "June (Brianna Denski) adalah seorang anak jenius dan memiliki imajinasi yang sangat tinggi. June selalu diandalkan teman-temannya dalam hal perakitan barang yang rusak. June berimajinasi memiliki taman bermain hasil buatannya sendiri. Ibu (Jennifer Garner) dan Ayah (Matthew Broderick) June adalah orang tua yang supportif.Mereka ‘menyulap’ rumah mereka menjadi sebuah taman bermain seperti yang ada di imajinasi June.Suatu ketika, terjadi masalah yang menimpa Ibu June sehingga menyebabkannya harus pindah ke luar kota yang sangat jauh dalam waktu yang sangat lama. June yang kini tinggal sendiri tanpa ibunya kehilangan semangat untuk mengurus taman bermain buatannya tersebut. Semua mainan-mainan di rumahnya, June simpan di kardus dan tidak disentuh sama sekali setelahnya.Beberapa tahun kemudian, June harus mendatangi sebuah Kamp Matematika dalam rangka tur sekolahnya. Di tengah jalan, June berpisah dari rombongannya dan memasuki sebuah hutan ‘ajaib’. Siapa sangka bahwa di dalam hutan tersebut terdapat taman bermain bersama binatang-binatang yang selama ini hanya ada di imajinasinya saja."
        );

        tambahFilm(film4, db);
        idFilm++;


    }
}
