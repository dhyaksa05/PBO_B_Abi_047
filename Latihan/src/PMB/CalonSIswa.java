package PMB;

public class CalonSIswa {
    private String nama;
    private String [] pilihanJurusan = new String[3];

    public CalonSiswa(String namaDepan, String namaBelakang, String [] pilihanJurusan){
        setNama(namaDepan,namaBelakang);
        setPilihanJurusan(pilihanJurusan);

    }

    public CalonSiswa(String namaDepan, String namaBelakang, String pilihanJurusan1, String pilihanJurusan2, String pilihanJurusan3){
        setNama(namaDepan,namaBelakang);
        setPilihanJurusan(pilihanJurusan1,pilihanJurusan2,pilihanJurusan3)
    }

    public void SetNama(String namaDepan, String namaBelakang){
        this.nama = namaDepan+ " "+namaBelakang;
    }

    public void setPilihanJurusan(String[] pilihanJurusan){ this.pilihanJurusan = pilihanJurusan;}
    public void setPilihanJurusan(String pj1, String pj2, String pj3){
        this.pilihanJurusan[0]=pj1;
        this.pilihanJurusan[1]=pj2;
        this.pilihanJurusan[2]=pj3;
    }

    public String getNama() {return nama;}

    public String getPilihanJurussan() {return pilihanJurusan}

    public void printCalonSiswa(){
        System.out.println("Nama Siswa : "+this.nama);
        for(String 1: this.pilihanJurusan){
            System.out.println("Pilihan Jurusan di UMM : "+i);
        }
    }
}
