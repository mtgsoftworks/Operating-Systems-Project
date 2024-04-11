# Operating-Systems-Project

Özet
Java programlama dili kullanılarak sınırlı kullanılabilir kaynakların kısıtlamaları içinde
çalışan dört seviyeli öncelikli proses görevlendiricisine sahip bir çoklu programlama sistemi simüle edilmiştir.
Not:	Ödevde	istenilen	jar	dosyası	projenin	dist	klasörü	içerisindedir.

Anahtar Kelimeler: Dispatcher, FCFS, proses, round robin, kuantum, geri besleme, java, zaman aşımı

GELİŞTİRİLEN YAZILIM
Program çalıştırıldığında giriş dosyasından okunan prosesler Dispatcher nesnesinde bulunun ve prosesleri tutan kuyruğa yerleştirilir. Proseslerin varış zamanı, öncelik değeri, işlenme zamanı, gibi özellikleri vardır. Bunların haricinde belirli donanım kaynak kısıtlamalarına (CD Sürücü, Bellek, Modem, Yazıcı, Tarayıcı) tabidir. Kuyruğa yerleştirilen prosesler, girilen kaynak mevcut ise öncelikle istenilen donanım tahsis edilir daha sonra özelliklerine göre sıralanarak uygun öncelikli kuyruklara yerleştirilir.

Gelen Procces’lerin Sıralanması ve Kuyruklara Alınması

Giris dosyasından gelen process’leri öncelik değerlerine göre öncelikli kuyruklara yerleştiriyoruz. Dört öncelik değeri için dört adet kuyruğumuz bulunmakta.

Ardından process’ler karışık sırayla geldiği için ArrivalTime değişkenlerine göre sıraladık. Bu bizi process’leri her seferinde aramaktan kurtardı. Time değişkenine göre ilk elemana bakılıyor eğer ilk elemanın ArrivalTime’ı, Time değerimizden büyükse devamına bakmaya gerek kalmamaktadır. Sıralama fonksiyonunun içine bakacak olursak; Gelen kuyruğu SelectionShort algoritmasıyla beraber ArrivalTime’a göre sıraladık.

Görevlendiricinin Çalışması

Görevlendirici, sıfır öncelik değerine sahip prosesleri bulunduran kuyruğu gezmekle başlar. Görevlendirinceye gelen proses varsa bu proses FCFS algoritmasıyla eğer yeterli kaynak varsa (İstenilen bütün kaynakların mevcut olması gerekir, aksi halde işlem askıya alınır) ve max bellek sınırını aşmıyor ise [Gerçek-zamanlı proses için max(64MB), Diğer prosesler için max(960 MB)] işletilir. Sonlanana kadar devam eder. Bu kuyruk gezilirken aynı zamanda get_Resource() ve release_Resource() fonksiyonları ile gerekli yerlerde istenilen kaynaklar tahsis edilir veyahut serbest bırakılır ve böylelikle diğer kuyruklar da kontrol edilir. Kontrol için feedback() ve timeOutCheck fonksiyonları çağrılır. Eğer sıfır öncelikli proseslerin bulunduğu kuyrukta proses yoksa feedback fonksiyonunda öncelik sırasına göre prosesler
 
çalıştırılır. Kuantum değeri bir olduğu için feedback’de her proses bir saniye çalışır ve prosesin önceliği düşürülerek askıya alınır. Askıya alınan prosesler farklı bir kuyrukta tutulur ve tekrar sıra kendine gelene kadar o kuyrukta bekler.
Tüm bu işlemler timer değişkeniyle senkronize edilir. timeOutCheck fonksiyonuyla da yirmi saniye çalışmayan prosesler tespit edilerek kuyruktan çıkarılır, kaynaklar iade edilerek her bir donanım yerel değişkeni güncellenir ve prosesin zaman aşımına uğradığını gösteren mesajlar konsola yazılır.

Olası İyileştirmeler

Görevlendiricide kullanılan sıralama algoritmasında daha efektif bir seçim yapabilirdik. Onun haricinde görevlendirici şuanda sadece 4 seviye için çalışmaktadır daha yüksek seviyelerde ek olarak kod yazmamız gerekli ve öncelik değiştirmek istediğimizde maliyeti yüksek olabilir. Donanım kaynak kısıtlamasında get_Resource() ve release_Resource() sınıfları her ne kadar kullanışlı gözükse de ayrı process kanalının yerel değişkenlerini atamda, takip etmek zor ve karmaşıktır. Bu fonksiyonların giriş parametresi olarak istenilen process kanalına göre otomatikleştirilmiş kaynak kısıtlaması yapması hem daha anlaşılır olacak ve olası eklemelerde iş yükünü azaltacaktır.

Procces Sınıfı

Dosyadan okunan her proses için kendi yazdığımız proses sınıfından nesneler oluştururuz ve gerekli değişkenlerin atamasını yaparız.

Görevlendirici tarafından prosesin çalışması için proses sınıfımızın execute fonksiyonu çağrılmalıdır. Bu fonksiyon Java proseslerini kullanarak daha önce oluşturduğumuz jar dosyasını çalıştırır ve konsola prosesin bilgileri yazdırılır. Java prosesi Runtime.exec() kullanılarak çağrılır.

Proses mesajlarının renkli çıkabilmesi için ColoredSystemOutPrintLn sınıfındaki ANSI escape sequence’ları kullanılmıştır. Her yeni proses oluştuğunda COLORS array’indeki ANSI kodlarından biri o prosese özel bir colorId property’si şeklinde atanır. Bu colorId’ler, proses mesajlarının stringlerinde başa gelir. Proses mesajları ekrana renkli bir şekilde çıkar. 

Görevlendirici Karşılaştırması

Görevlendirici modülünün işletim sistemi üzerindeki görevi gelen işlemlerin CPU’yu kullanımını düzenler, process’leri uygun pozisyonlara koyar ve kullanıcı modunu değiştirmeyi sağlar. Görevlendirici modülleri tek bir amaca hizmet etmediği için birden fazla çeşiti vardır. Bu yüzden kendi ihtiyaçlarımıza özel olarak görevlendirici algoritması yazabiliriz. İşletim sisteminin kullandığı görevlendirici şemasına bakacak olursak (bkz Görsel:1), gelen process’ler hazır kuyruğuna alınıyor ve sırası gelen process çalıştırılmak üzere CPU’ya yönlendiriliyor.

Bizim görevlendirici mekanizmamıza bakacak olursak :
 
Gelen processler yeterli kadar istenilen kaynak varsa ve max bellek sınırını aşmıyor ise [Gerçek-zamanlı proses için max(64MB), Diğer prosesler için max(960 MB)] önceliklerine göre cpu kullanımına izin veriliyor ve ardından önceliği 0 değilse bir alt seviyeye düşürülüp işleme devam ettiriliyor. Önceliği sıfır olmayan bir process işlem görürken sıfır öncelikli bir process gelirse işlem gören process askı kuyruğuna alınıyor ve sıfır öncelikli process’in işlemi bitene kadar bekliyor bekleme süresi 20 saniyeyi aşarsa process zaman aşımına uğrayıp siliniyor. Gerçek işletim sistemi process’leri alırken hazır kuyruğundan alıp işlettikten sonra aynı listenin sonuna tekrar ekleyerek devam etmekte biz bunu modellerken 2 adet askı kuyruğu kullandığımız için depolama performansımız düşük kaldı.
