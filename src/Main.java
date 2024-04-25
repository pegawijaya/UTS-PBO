import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ItemMakanan {
    protected String nama;
    protected double harga;
    
    public ItemMakanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama + ", Harga: $" + harga);
    }
}

class MakananUtama extends ItemMakanan {
    public MakananUtama(String nama, double harga) {
        super(nama, harga);
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("[Makanan Utama] " + nama + ", Harga: $" + harga);
    }
}

class MakananPenutup extends ItemMakanan {
    public MakananPenutup(String nama, double harga) {
        super(nama, harga);
    }
    
    @Override
    public void tampilkanInfo() {
        System.out.println("[Makanan Penutup] " + nama + ", Harga: $" + harga);
    }
}

class Menu {
    private Map<String, ItemMakanan> daftarMenu;
    
    public Menu() {
        daftarMenu = new HashMap<>();
    }
    
    public void tambahItemMakanan(String nama, double harga, String jenis) {
        switch (jenis) {
            case "MakananUtama":
                daftarMenu.put(nama, new MakananUtama(nama, harga));
                break;
            case "MakananPenutup":
                daftarMenu.put(nama, new MakananPenutup(nama, harga));
                break;
            default:
                System.out.println("Jenis makanan tidak valid!");
        }
    }
    
    public void tampilkanMenu() {
        System.out.println("Daftar Menu:");
        for (ItemMakanan item : daftarMenu.values()) {
            item.tampilkanInfo();
        }
    }
    
    public ItemMakanan getItemMakanan(String nama) {
        return daftarMenu.get(nama);
    }
}

class Pesanan {
    Map<ItemMakanan, Integer> itemPesanan;
    
    public Pesanan() {
        itemPesanan = new HashMap<>();
    }
    
    public void tambahItem(ItemMakanan item, int jumlah) {
        if (itemPesanan.containsKey(item)) {
            itemPesanan.put(item, itemPesanan.get(item) + jumlah);
        } else {
            itemPesanan.put(item, jumlah);
        }
    }
    
    public double hitungTotal() {
        double total = 0;
        for (ItemMakanan item : itemPesanan.keySet()) {
            total += item.getHarga() * itemPesanan.get(item);
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        
        menu.tambahItemMakanan("Steak", 15.99, "MakananUtama");
        menu.tambahItemMakanan("Pasta", 12.50, "MakananUtama");
        menu.tambahItemMakanan("Kue", 6.99, "MakananPenutup");
        menu.tambahItemMakanan("Es Krim", 4.50, "MakananPenutup");
        
        Pesanan pesanan = new Pesanan();
        
        while (true) {
            System.out.println("\n=== Sistem Pemesanan Makanan ===");
            System.out.println("1. Tampilkan Menu");
            System.out.println("2. Tambah Item ke Pesanan");
            System.out.println("3. Lihat Pesanan");
            System.out.println("4. Checkout");
            System.out.println("5. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            int pilihan = scanner.nextInt();
            
            switch (pilihan) {
                case 1:
                    menu.tampilkanMenu();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Masukkan nama item: ");
                    String namaItem = scanner.nextLine();
                    System.out.print("Masukkan jumlah: ");
                    int jumlah = scanner.nextInt();
                    ItemMakanan item = menu.getItemMakanan(namaItem);
                    if (item != null) {
                        pesanan.tambahItem(item, jumlah);
                        System.out.println("Item berhasil ditambahkan ke pesanan!");
                    } else {
                        System.out.println("Item tidak ditemukan dalam menu!");
                    }
                    break;
                case 3:
                    System.out.println("Pesanan:");
                    for (ItemMakanan itemPesanan : pesanan.itemPesanan.keySet()) {
                        System.out.println("Nama: " + itemPesanan.getNama() + ", Jumlah: " + pesanan.itemPesanan.get(itemPesanan));
                    }
                    System.out.println("Total: $" + pesanan.hitungTotal());
                    break;
                case 4:
                    System.out.println("Total pembayaran: $" + pesanan.hitungTotal());
                    System.out.println("Terima kasih atas pesanan Anda! Selamat menikmati makanan!");
                    pesanan = new Pesanan();
                    break;
                case 5:
                    System.out.println("Keluar dari Sistem Pemesanan Makanan. Sampai jumpa!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan masukkan pilihan yang benar.");
            }
        }
    }
}
