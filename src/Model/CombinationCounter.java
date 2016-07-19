package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Enumerations.CardType;
import Model.Enumerations.CombinationType;

public abstract class CombinationCounter {
	protected List<Card> cards = new ArrayList<Card>();
	protected Table aTable;
	protected CombinationType combination;
	
	public Combination getCombination(){
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(this.aTable.getCards());
		allCards.addAll(this.cards);
		Collections.sort(allCards);
		ArrayList<Integer> counts = new ArrayList<Integer>();
		ArrayList<ArrayList<Card>> pairs = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> toStraight = new ArrayList<Card>();		
		ArrayList<Card> toFlash = new ArrayList<Card>();
		boolean flag = true;
		int sp=0;
		int pointerOfStraight = 0;
		boolean isFlash =false;
//		int[] typesForFlash = new int[4];
		//we calculate flash. 
		//if one the three cards has a equal CardType 4 another card 
		for(int i=0;i<3;i++){
			isFlash = true;
			for(int j=0;j<allCards.size();j++){
				if(i!=j && allCards.get(i).getType().equals(allCards.get(j).getType())){
					isFlash&=true;
					toFlash.add(allCards.get(j));
				}else if(i!=j && !allCards.get(i).getType().equals(allCards.get(j).getType()))
					isFlash=false;
			}
			if(isFlash){
				toFlash.add(i, allCards.get(i));
				break;
			}
				
		}

		int count2straight=0;
		for(int i=allCards.size()-1;i>allCards.size()-3;i--){
			count2straight=i;
			while(count2straight>0 && allCards.get(count2straight).getValue().getValue()==allCards.get(count2straight-1).getValue().getValue()-1){
				pointerOfStraight++;
				toStraight.add(allCards.get(count2straight--));			
			}
			if(pointerOfStraight>=5)
				break;
		}		
		if(pointerOfStraight>=5){
			toStraight.add(0,allCards.get(count2straight));			
			boolean straightFlash=true;
			for(int j=1;j>=0;j--){
				for(int i=j;i<j+5;i++)
					straightFlash&=toStraight.get(i).getType().equals(toStraight.get(i+1).getType());
				if(straightFlash){
					toStraight = (ArrayList<Card>) toStraight.subList(pointerOfStraight-5-j-1, toStraight.size()-j-1);
					return new Combination(CombinationType.StraightFlash,toStraight);
				}
			}
				
			
			toStraight = (ArrayList<Card>) toStraight.subList(pointerOfStraight-5, toStraight.size());
		}
		
		do{
			int count=0;
			ArrayList<Card> cards = new ArrayList<Card>();			
			while((sp+count+1)<allCards.size() && allCards.get(sp+count).getValue().getValue()==allCards.get(sp+count+1).getValue().getValue() ){
				count++;
				cards.add(allCards.get(sp+count));
			}
			if(count>0){
				cards.add(0,allCards.get(sp));
				pairs.add(cards);
			}
			sp+=count+1;
		}while(sp<allCards.size());
		for(int i=0;i<pairs.size();i++){
			switch(pairs.get(i).size()){
			case 2:
				for(int j=0;j<pairs.size();j++){
					if(i!=j && pairs.get(j).size()==2){
						if(pointerOfStraight>=5)
							return new Combination(CombinationType.Straight,toStraight);
						else if(isFlash)
							return new Combination(CombinationType.Flash,toFlash);
						for(int ii=0;ii<2;ii++){
							allCards.remove(pairs.get(j).get(ii));
							allCards.remove(pairs.get(i).get(ii));
						}
						ArrayList<Card> twoPair = new ArrayList<Card>();
						twoPair.addAll(pairs.get(i));
						twoPair.addAll(pairs.get(j));
						twoPair.add(allCards.get(allCards.size()-1));
						return new Combination(CombinationType.TwoPairs,twoPair);
					}else if(i!=j && pairs.get(j).size()==3){
						ArrayList<Card> fullHouse = new ArrayList<Card>();
						fullHouse.addAll(pairs.get(i));
						fullHouse.addAll(pairs.get(j));
						return new Combination(CombinationType.FullHouse,fullHouse);
					}else if(i!=j && pairs.get(j).size()==4){
						ArrayList<Card> quad = new ArrayList<Card>();
						for(int ii=0;ii<pairs.get(j).size();ii++){
							allCards.remove(pairs.get(j).get(ii));							
						}
						quad.addAll(pairs.get(j));
						quad.add(allCards.get(allCards.size()-1));
						return new Combination( CombinationType.Quads,quad);
					}
				}
				return new Combination(CombinationType.Pair,null);
			case 3:
				for(int j=0;j<pairs.size();j++){
					if(i!=j && pairs.get(j).size()==2){						
						return new Combination(CombinationType.FullHouse,null);
					}else if(i!=j && pairs.get(j).size()==4){
						return new Combination(CombinationType.Quads, null);
					}
				}
				if(pointerOfStraight>=5)
					return new Combination(CombinationType.Straight,toStraight);				
				return new Combination(CombinationType.ThreeOfKind,null);
			case 4:
				ArrayList<Card> quad = new ArrayList<Card>();
				for(int ii=0;ii<pairs.get(i).size();ii++){
					allCards.remove(pairs.get(i).get(ii));							
				}
				quad.addAll(pairs.get(i));
				quad.add(allCards.get(allCards.size()-1));
				return new Combination( CombinationType.Quads,quad);
				
			}
		}
		return new Combination(CombinationType.HighCard,null);
	}
	private ArrayList<Card> findTheSameCard(Card card,List<Card> source){
		ArrayList<Card> result = new ArrayList<Card>();
		for(Card i: source){
			if(i.getValue().getValue()==card.getValue().getValue()){
				result.add(i);
			}
		}
		return result;
	}
	public void setCards(ArrayList<Card> hand){
		this.cards=hand;
	}
	public void setTable(Table aTable){
		this.aTable = aTable;
		
	}
}
