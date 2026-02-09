package tp1.p2.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Record {
	private int recordAct;
	private Level level;
	
	private static List<Record> records;
	
	private Record() {
		
	}
	
	private Record(int record, Level level) {
		this.recordAct = record;
		this.level = level;
	}
	
	public static Record loadRecord(Level level) throws GameException  {
		records = new ArrayList<>();
		Record dev = new Record();
		String l = null;
		
		try (BufferedReader inChars = new BufferedReader(new FileReader(Messages.RECORD_FILENAME))){
			while((l = inChars.readLine()) != null) {
				String[] line = l.toUpperCase().trim().split(":");
				try {
					if (Level.valueOfIgnoreCase(line[0]) == null) {
						throw new RecordException(Messages.RECORD_READ_ERROR);
					}
					records.add(new Record(Integer.parseInt(line[1]), Level.valueOfIgnoreCase(line[0])));
					
				}
				catch (NumberFormatException e) {
					throw new RecordException(Messages.RECORD_READ_ERROR, e);
				}
			}
		}
		catch (IOException io) {
			throw new RecordException(Messages.RECORD_READ_ERROR, io);
		}
		
		boolean found = false;
		for (Record r : records) {
		    if (r.level.equals(level)) {
		    	found = true;
		    	dev = r;
		    }
		}
		if (!found) {
			dev = new Record(0, level);
			records.add(dev);
		}
		return dev;
	}
	
	public boolean update(int score) {
		if (score > recordAct) {
			recordAct = score;
			return true;
		}
		return false;
	}
	
	public void save() throws GameException {
		StringBuilder str = new StringBuilder();
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME))) {
			for (Record r : records) {
				if (r.level.equals(this.level)) {
					r.recordAct = this.recordAct;
				}
				str.append(r.level);
				str.append(":");
				str.append(r.recordAct);
				str.append(Messages.LINE_SEPARATOR);
		    }
			outChars.write(str.toString());
		}

		catch (IOException io) {
			throw new RecordException(Messages.RECORD_READ_ERROR, io);
		}	
	}
	
	public int getRecord() {
		return this.recordAct;
	}
	
}
