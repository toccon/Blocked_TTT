import java.util.*;
/*#################################### Assignment 2: BlockedTicTacToe  #############################################
# Nicholas Tocco
# Student Number: 250909544
# CS2210
# October 19, 2017
#
# This class implements a dictionary using a hash table with seperate chaining (linked lists)
###################################################################################################################*/
public class TTTDictionary implements TTTDictionaryADT {
	// instance variables
	private int count;
	private final int DEFAULT_SIZE = 4933;
	private LinkedList<TTTRecord>[] htable;
	private int tablesize;

	/**
	 * constructor creates an array of linked list of specified size
	 * 
	 * @param size
	 *            size of array
	 */
	public TTTDictionary(int size) {
		htable = new LinkedList[size];
		count = 0;
		tablesize = size;
	}

	/**
	 * constructor with no size specified created a dictionary of default size
	 */
	public TTTDictionary() {
		htable = new LinkedList[DEFAULT_SIZE];
		count = 0;
		tablesize = DEFAULT_SIZE;
	}

	/**
	 * inserts the given TTTRecord in the dictionary
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		// compute hash function on record.getconfig
		// store item in the hash table using linked lists, if the index is
		// empty create a new linked list and
		// make the given record the head(return 0), otherwise just add it on to
		// the linked list (return 1)
		int hashval = hashfunction(record.getConfiguration(), 33);
		if (get(record.getConfiguration()) != null) {
			throw new DuplicatedKeyException(record.getConfiguration());
		} else if (htable[hashval] == null) {
			htable[hashval] = new LinkedList<TTTRecord>();
			htable[hashval].add(record);
			count += 1;
			return 0;
		} else {
			htable[hashval].add(record);
			count += 1;
			return 1;
		}
	}

	/**
	 * removes the record with the given key config from the dictionary
	 */
	public void remove(String config) throws InexistentKeyException {
		// apply hash function to configuration which should return the index of
		// the
		// record
		// if the configuration is found at the index, remove it
		int hashvalue = hashfunction(config, 33);
		if (get(config) == null) {
			throw new InexistentKeyException(config);
		} else {
			Iterator<TTTRecord> iter = htable[hashvalue].iterator();
			while (iter.hasNext()) {
				TTTRecord x = iter.next();
				if (x.getConfiguration().equals(config)) {
					htable[hashvalue].remove(x);
					count -= 1;
					break;
				}
			}
		}
	}

	/**
	 * a method which returns the TTTRecord stored in the dictionary for the
	 * given configuration, or null if the configuration is not in the
	 * dictionary
	 */
	public TTTRecord get(String config) {
		// A method which returns the TTTRecord stored in the dictionary for the
		// given configuration,
		// or null if the configuration is not in the dictionary
		// apply hash function to the configuration, check if it is in the hash
		// table
		// if it is in the hash table then return the TTTRecord, if not then
		// return null
		int hashvalue = hashfunction(config, 33);
		if (htable[hashvalue] == null) {
			return null;
		}
		if (htable[hashvalue] != null) {
			for (TTTRecord x : htable[hashvalue]) {
				if (x.getConfiguration().equals(config)) {
					return x;
				}
			}
		}
		return null;
	}

	/**
	 * returns number of elements in the dictionary
	 */
	public int numElements() {
		return count;
	}

	/**
	 * polynomial hash function and compression map for table of size table size
	 * 
	 * @param config
	 *            string configuration
	 * @param x
	 * @return hashed and compressed integer which fits in the array to store or
	 *         retrieve records at
	 */
	private int hashfunction(String config, int x) {
		int value = ((int) config.charAt(config.length() - 1));
		for (int i = config.length() - 2; i >= 0; i--) {
			value = ((value * x) + (int) config.charAt(i)) % tablesize;
		}
		return value;
	}
}
