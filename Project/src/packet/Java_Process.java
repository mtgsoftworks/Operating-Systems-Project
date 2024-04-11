package packet;
import static packet.ColoredSystemOutPrintln.*;

public class Java_Process {

	//Her proses için bu program çalıştırılır
	public static void main(String[] args) {
		int colorId = Integer.parseInt(args[8]);
		System.out.println(COLORS[colorId] + "(id:" + args[0] + "  oncelik:" + args[1] + "  kalan sure:" + args[2] + " sn"
		+ " Mbayt:" + args[3] + " yazicilar:" + args[4] + " tarayicilar:" + args[5] + " modemler:" + args[6] 
		+ " CDs:" + args[7] + ")" + ANSI_RESET);
	}
}
