
public class Platform implements PlatformADT {

	private int capacity;
	private StackADT<Train> platform;
	
	public Platform (int capacity) {
		this.capacity = capacity;
		platform = new SimpleStack<Train>(capacity);
	}

	@Override
	public void put(Train item) throws FullPlatformException {
		//input validation
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		if (platform.isFull()){
			throw new FullPlatformException();
		}
		
		try {
			platform.push(item);
		}
		catch (FullStackException ex){
			System.out.println("FullStackException Caught.");
		}
	}

	@Override
	public Train get() throws EmptyPlatformException {
		//input validation
		if (platform.isEmpty()){
			throw new EmptyPlatformException();
		}
		
		Train toRet = null;
		
		try {
			toRet = platform.pop();
		}
		catch (EmptyStackException ex){
			System.out.println("EmptyStackException Caught.");
		}
		
		return toRet;
	}

	@Override
	public Train check() throws EmptyPlatformException {
		if (platform.isEmpty()){
			throw new EmptyPlatformException();
		}
		
		Train toRet = null;
		
		try {
			toRet = platform.peek();
		}
		catch (EmptyStackException ex){
			System.out.println("EmptyStackException Caught.");
		}
		
		return toRet;

	}

	@Override
	public boolean isEmpty() {
		return platform.isEmpty();
	}

	@Override
	public boolean isFull() {
		return platform.isFull();
	}
	
}
