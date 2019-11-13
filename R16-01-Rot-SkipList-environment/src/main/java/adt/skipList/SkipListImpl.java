package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		
		SkipListNode<T> auxNode = this.root;
		for (int i = (this.maxHeight - 1); i >= 0; i--) {
			while (auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward(0);
		
		if (auxNode.getKey() == key) {
			auxNode.setValue(newValue);
		} else {
			auxNode = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				auxNode.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = auxNode;
			}
		}		
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> auxNode = this.root;
		
		for (int i = (this.maxHeight - 1); i >= 0; i--) {
			while (auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward(0);
		
		if (auxNode.getKey() == key) {
			int i = 0;
			while (i < this.maxHeight && update[i].getForward(i) == auxNode) {
				update[i].getForward()[i] = auxNode.getForward(i);
				i++;
			}
		}		
	}

	@Override
	public int height() {
		int resp = 0;
		
		SkipListNode<T> auxNode = this.root.getForward(0);
		while (!auxNode.equals(NIL)) {
			if (resp < auxNode.height()) {
				resp = auxNode.height();				
			}
			auxNode = auxNode.getForward(0);
		}
		return resp;	
	}

	@Override
	public SkipListNode<T> search(int key) {
		
		SkipListNode<T> auxNode = this.root;	
		for (int i = (this.maxHeight - 1); i >= 0; i--) {
			while (auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
		}
		auxNode = auxNode.getForward(0);
		
		if (auxNode.getKey() != key) {
			auxNode = null;
		}
		return auxNode;
	}

	@Override
	public int size() {
		int resp = 0;
		
		SkipListNode<T> aux = this.root.getForward(0);		
		while (!aux.equals(NIL)) {
			resp++;
			aux = aux.getForward(0);
		}
		return resp;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size() + 2;
		SkipListNode<T>[] resp = new SkipListNode[size];
		SkipListNode<T> auxNode = this.root;
		
		int i = 0;
		while (i != size) {
			resp[i] = auxNode;
			auxNode = auxNode.getForward(0);
			i++;
		}
		return resp;
	}

}
