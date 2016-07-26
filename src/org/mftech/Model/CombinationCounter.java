package org.mftech.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CombinationType;


public abstract class CombinationCounter {
	protected List<Card> cards = new ArrayList<Card>();
	protected Table aTable;
	protected CombinationType combination;
	private final static int SIZE_OF_COMBINATION = 5;
	
	public Combination getCombination(){
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(this.aTable.getCards());
		allCards.addAll(this.cards);
		if(allCards.size()==0)
			return new Combination(CombinationType.NoCombination,new ArrayList<Card>());
		Collections.sort(allCards);
		ArrayList<ArrayList<Card>> pairs = new ArrayList<ArrayList<Card>>();
		int sp=0;
		boolean isFlash =false;
		//we calculate flash. 
		//if one the three cards has a equal CardType 4 another card 
		Combination flash = isFlash(allCards);
		if(flash!=null)
			isFlash =true;
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
		Combination straight = isStraight(allCards,pairs);
		boolean isStraight = false;
		if(straight!=null){
			if(straight.getType().equals(CombinationType.StraightFlash))
				return straight;
			else 
				isStraight = true;
		}
		for(int i=0;i<pairs.size();i++){
			switch(pairs.get(i).size()){
			case 2:
				for(int j=0;j<pairs.size();j++){
					if(i!=j && pairs.get(j).size()==2){
						if(isFlash)
							return flash;
						if(isStraight)
							return straight;
							
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
				if(isFlash)
					return flash;
				if(isStraight)
					return straight;
				for(int countCard=0;countCard<pairs.get(i).size();countCard++)
					allCards.remove(pairs.get(i).get(countCard));
				ArrayList<Card> pair = new ArrayList<Card>();
				pair.addAll(pairs.get(i));
				for(int countCard = allCards.size()-1;pair.size()<this.SIZE_OF_COMBINATION&&countCard>=0;countCard--)
					pair.add(allCards.get(countCard));
				return new Combination(CombinationType.Pair,pair);
			case 3:
				for(int j=0;j<pairs.size();j++){
					if(i!=j && pairs.get(j).size()==2){						
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
				if(isFlash)
					return flash;
				if(isStraight)
					return straight;
			
				for(int countCard=0;countCard<pairs.get(i).size();countCard++)
					allCards.remove(pairs.get(i).get(countCard));
				pair = new ArrayList<Card>();
				pair.addAll(pairs.get(i));
				for(int countCard = allCards.size()-1;pair.size()<SIZE_OF_COMBINATION&&countCard>=0;countCard--)
					pair.add(allCards.get(countCard));
				return new Combination(CombinationType.ThreeOfKind,pair);
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
		if(isFlash)
			return flash;
		if(isStraight)
			return straight;
		ArrayList<Card> resultWithHighCard = new ArrayList<Card>();
		for(int i=allCards.size()-1;resultWithHighCard.size()<SIZE_OF_COMBINATION && i>=0;i--)
				resultWithHighCard.add(allCards.get(i));
		return new Combination(CombinationType.HighCard,resultWithHighCard);
	}
	
	public void setCards(ArrayList<Card> hand){
		this.cards=hand;
	}
	public void setTable(Table aTable){
		this.aTable = aTable;		
	}
	private Combination isFlash(List<Card> allCards){
		Card[][] typesCard = new Card[4][allCards.size()];
		for(int i=0;i<allCards.size();i++){
			if(allCards.get(i).getType().equals(CardType.Clubs)){
				typesCard[0][i]=allCards.get(i);
			}else if(allCards.get(i).getType().equals(CardType.Diamonds)){
				typesCard[1][i]=allCards.get(i);
			}else if(allCards.get(i).getType().equals(CardType.Hearts)){
				typesCard[2][i]=allCards.get(i);
			}else if(allCards.get(i).getType().equals(CardType.Spades)){
				typesCard[3][i]=allCards.get(i);
			}
		}
		boolean flash = false;
		int counter =0;
		for(int i=0;i<typesCard.length;i++){
			counter =0;
			for(int j=0;j<typesCard[i].length;j++){
				if(typesCard[i][j]!=null){
					counter++;
				}					
			}
			if(counter>=5){
				flash = true;
				counter = i;
				break;
			}
		}
		if(flash){
			ArrayList<Card> array2result = new ArrayList<Card>();
			for(int i=0;i<typesCard[counter].length;i++){
				if(typesCard[counter][i]!=null){
					array2result.add(typesCard[counter][i]);
				}
			}
			Combination result = new Combination(CombinationType.Flash,array2result);
			return result;
		}		
		return null;
	}
	
	private Combination isStraight(List<Card> cards,ArrayList<ArrayList<Card>> pairs){
		int counter = 0;
		boolean isFirst = true;
		boolean flag = false;
		int pointer2next = 1;
		ArrayList<Card> straight = new ArrayList<Card>();		
		for(int i=cards.size()-1;i>0;i--){
			pointer2next = 1;
			boolean flag2endFor=false;
			do{
				if(cards.get(i).getValue().getValue()==(cards.get(i-pointer2next).getValue().getValue()+1)){
					if(isFirst){
						straight.add(cards.get(i));					
						isFirst = false;
						counter++;
					}
					straight.add(cards.get(i-pointer2next));
					counter++;
					i=i-pointer2next+1;
					pointer2next = 1;					
					flag = false;
				}else if(i-pointer2next-1<0){
					flag = false;
					flag2endFor=true;
				}else{
					pointer2next++;
					flag = true;
				}
			}while(flag);
		}
		Combination lowStraight=this.checkLowStraight(cards, pairs);
		if(counter>=5){
			if(pairs.size()==0){
				Combination straightFlash = isFlash(straight);
				if(straightFlash!=null)
						return straightFlash.setCombinationType(CombinationType.StraightFlash);
				
				if(lowStraight==null)
					return new Combination(CombinationType.Straight,straight);
				else
					if(lowStraight.getType().compareTo(CombinationType.StraightFlash)==0){
						return lowStraight;
					}else
						return new Combination(CombinationType.Straight,straight);
			}				
			else{
				for(ArrayList<Card> i: pairs){//check pairs
					for(Card j:i){//
						for(Card k:straight){//find and check flashstraight with another card in pair
							if(j.compareTo(k)==0){
								ArrayList<Card> anotherStraight = (ArrayList<Card>)straight.clone();
								anotherStraight.remove(k);
								anotherStraight.add(j);
								Combination straightFlash = isFlash(anotherStraight);
								if(straightFlash!=null)
										return straightFlash.setCombinationType(CombinationType.StraightFlash);
							}
						}
					}
				}
				if(lowStraight==null)
					return new Combination(CombinationType.Straight,straight);
				else
					if(lowStraight.getType().compareTo(CombinationType.StraightFlash)==0){
						return lowStraight;
					}else
						return new Combination(CombinationType.Straight,straight);
			}
		}
		
		return lowStraight;
	}
	private Combination checkLowStraight(List<Card> cards,ArrayList<ArrayList<Card>> pairs){
		boolean[] b = new boolean[cards.size()];
		ArrayList<Card> a = new ArrayList<Card>();
		for(int i=0;i<cards.size();i++){			
			if(cards.get(i).compareTo(new Card(CardType.Clubs,CardValue.Ace))==0){
				b[i]=true;
			}
			if(cards.get(i).compareTo(new Card(CardType.Clubs,CardValue.Two))==0){
				b[i]=true;
			}
			if(cards.get(i).compareTo(new Card(CardType.Clubs,CardValue.Three))==0){
				b[i]=true;
			}
			if(cards.get(i).compareTo(new Card(CardType.Clubs,CardValue.Four))==0){
				b[i]=true;
			}
			if(cards.get(i).compareTo(new Card(CardType.Clubs,CardValue.Five))==0){
				b[i]=true;
			}
			if(b[i])
				a.add(cards.get(i));
		}		
		if(a.size()==5){
			Combination comb = isFlash(a);
			if(comb==null)
				return new Combination(CombinationType.Straight,a);
			else
				return comb.setCombinationType(CombinationType.StraightFlash);
		}else if(a.size()>5){
			Combination comb = isFlash(a);
			Collections.sort(a);			
			ArrayList<Integer> indexes2remove = new ArrayList<Integer>();
			for(int i =0;i<a.size()-1;i++){
				if(a.get(i).getValue().getValue()==a.get(i+1).getValue().getValue()){						
					indexes2remove.add(i);
				}
			}
			if(comb==null){
				for(Integer i:indexes2remove){
					a.remove(i);
				}
				return new Combination(CombinationType.Straight,a);
			}else{
				for(Integer i:indexes2remove){
					Card temp =a.remove(i.intValue());
					comb = isFlash(a);
					if(comb==null){
						a.remove(i);
						a.add(i,temp);
					}
				}
				return comb.setCombinationType(CombinationType.StraightFlash);
			}	
		}		
		return null;
		
	}
}
