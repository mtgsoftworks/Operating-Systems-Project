package packet;
import static packet.ColoredSystemOutPrintln.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static packet.ColoredSystemOutPrintln.COLORS;

public class myProcess extends Thread{
	
	private int _arrivalTime;
	private int _priority;
	private int _processorTime;
	private int _remainingTime;
	private int _Mbayt;
	private int _printers;
	private int _scanners;
	private int _modems;
	private int _CDs;
	private int _id;
	private int _lastCallTime;
	private int _colorId;
	static int colorNum = -1;

	public myProcess(String arrivalTime, String priority, String processorTime, String Mbayt, String printers, String scanners, String modems, String CDs){		
		_arrivalTime = Integer.parseInt(arrivalTime);
		_priority = Integer.parseInt(priority);
		_processorTime = Integer.parseInt(processorTime);
		_remainingTime = Integer.parseInt(processorTime);
		_Mbayt = Integer.parseInt(Mbayt);
		_printers = Integer.parseInt(printers);
		_scanners = Integer.parseInt(scanners);
		_modems = Integer.parseInt(modems);
		_CDs = Integer.parseInt(CDs);
		_lastCallTime = _arrivalTime;
		_id = Dispatcher.idCounter;
		Dispatcher.idCounter++;
		
		//her proses oluşturulduğunda farklı bir renk atanır
		colorNum++;
		if (colorNum >= COLORS.length) { colorNum = 0; }
		_colorId = colorNum;

	}
	
	public void execute() {// Java prosesleri çalıştırılır
		// çalıştırılmak istenen proses için bu fonksiyon kullanılır
		// proses, oluşturduğumuz jar dosyasını çalıştırır

		String priority = String.valueOf(this._priority);
		String remainingTime = String.valueOf(this._processorTime);
		String colorId = String.valueOf((this._colorId));
		String processId = String.valueOf((this._id));
		String Mbayt = String.valueOf((this._Mbayt));
		String printers = String.valueOf((this._printers));
		String scanners = String.valueOf((this._scanners));
		String modems = String.valueOf((this._modems));
		String CDs = String.valueOf((this._CDs));
		String jar = "java -jar Java_Process.jar";
		String parameter = jar + " " + processId + " " + priority + " " + remainingTime + " " + Mbayt + " " + printers 
		+ " " + scanners + " " + modems + " " + CDs + " " + colorId;

		try {
	          runProcess(parameter); 
	    } 
		catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	//bu fonksiyon static'ti ama şimdilik değiştirdim deneme için
	private void runProcess(String command) throws Exception {		
		// yeni bir proses oluşturulur ve çalıştırılır
        Process pro = Runtime.getRuntime().exec(command);    	
        printLines(pro.getInputStream());
        printLines(pro.getErrorStream());    
        pro.waitFor();// proses bitene kadar beklenir
      }
	
    private void printLines(InputStream ins) throws Exception {
    	// Ana programdan ayrı çalıştırılan proseslerin bilgileri ekrana yazdırılır
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(" " +line);
        }
      }

	  public void executeMessage() {
		System.out.print(COLORS[get_colorId()] + Dispatcher.timer + " sn proses basladi			" + ANSI_RESET);
	}
	
	public void runningMessage() {
		System.out.print(COLORS[get_colorId()] + Dispatcher.timer + " sn proses yurutuluyor		" + ANSI_RESET);
	}	

	public void suspendedMessage() { 
		System.out.print(COLORS[get_colorId()] + Dispatcher.timer + " sn proses askida			" + ANSI_RESET); }

	public void endMessage() {
		System.out.print(COLORS[get_colorId()] + Dispatcher.timer + " sn proses sonlandi		" + ANSI_RESET);
	}
	
	public void overTimeMessage() {
		System.out.print(COLORS[get_colorId()] + Dispatcher.timer + " sn proses zaman asimi 		" + ANSI_RESET);
	}

	public void realtime_process_deleteMessage()
	{
		System.out.print(COLORS[get_colorId()] + "(" + Dispatcher.timer + " sn Gerçek-zamanlı proses (64MB) tan daha fazla bellek talep ediyor - proses silindi)" + ANSI_RESET);
	}

	public void process_deleteMessage() {
		System.out.print(COLORS[get_colorId()] + "(" + Dispatcher.timer + " sn  Proses (960 MB) tan daha fazla bellek talep ediyor – proses silindi)" + ANSI_RESET);
	}

	public void  realtime_process_resourceError()
	{
		System.out.print(COLORS[get_colorId()] + "(" + Dispatcher.timer + " sn  Gerçek-zamanli proses çok sayida kaynak talep ediyor – proses silindi)" + ANSI_RESET);
	}
	
	public int get_arrivalTime() { return _arrivalTime; }
	public int get_priority() { return _priority; }
	public int get_processorTime() { return _processorTime; }
	public int get_RemainingTime() { return _remainingTime; }
	public int get_lastCallTime() {return _lastCallTime;}
	public int get_colorId() { return this._colorId; }
	public int get_Mbayt() {return _Mbayt;}
	public int get_printers() {return _printers;}
	public int get_scanners() {return _scanners;}
	public int get_modems() {return _modems;}
	public int get_CDs() {return _CDs;}


	
	public void set_lastCallTime(int _lastCallTime) {this._lastCallTime = _lastCallTime;}
	public void set_arrivalTime(int _arrivalTime) {this._arrivalTime = _arrivalTime;}
	public void set_priority(int _priority) {this._priority = _priority;}
	public void set_processorTime(int _processorTime) {this._processorTime = _processorTime;}
	public void set_Mbayt(int _Mbayt) {this._Mbayt = _Mbayt;}
	public void set_printers(int _printers) {this._printers = _printers;}
	public void set_scanners(int _scanners) {this._scanners = _scanners;}
	public void set_modems(int _modems) {this._modems = _modems;}
	public void set_CDs(int _CDs) {this._CDs = _CDs;}


}
	

