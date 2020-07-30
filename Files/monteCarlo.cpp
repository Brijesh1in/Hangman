#include <iostream>
#include<set>
#include<fstream>
#include <random>
#include <vector>
#include <algorithm>
#include <time.h>
using namespace std;

int guessCharacter(string &s){

	int n = s.size();
	assert(!s.empty());
	std::random_device dev;
    std::mt19937 rng(dev());
    std::uniform_int_distribution<std::mt19937::result_type> dist(0,n-1);
	return dist(rng);
}

int guessRequired(const string &s){

	int n = s.size();
	assert(n!=0);
	string guess = string(n , '?');
	string vowel = "aeiou";
	string consonant = "bcdfghjklmnpqrstvwxyz";
	int guessedVowels = 0;
	int tot = 0;
	std::random_device dev;
    std::mt19937 rng(dev());
    std::uniform_int_distribution<std::mt19937::result_type> dist(1,10);
	while(guess!=s){
		
		assert(tot < 26);
		int x = rand()%10 + 1;

		if(guessedVowels < 5){
			assert(!vowel.empty());
			if(x <= 7){
				int k = guessCharacter(vowel);
				for(int i = 0 ; i < n ; ++i){

					if(vowel[k]==s[i]){

						guess[i] = vowel[k];
					}
				}
				vowel.erase(vowel.begin() + k);
				++guessedVowels;
				++tot;
			}
			else if(!consonant.empty()){

				int k = guessCharacter(consonant);
				for(int i = 0 ; i < n ; ++i){

					if(consonant[k]==s[i]){

						guess[i] = s[i];
					}
				}
				consonant.erase(consonant.begin() + k);
				++tot;
			}
			else if(!vowel.empty()){

				int k = guessCharacter(vowel);
				for(int i = 0 ; i < n ; ++i){

					if(vowel[k]==s[i]){

						guess[i] = vowel[k];
					}
				}
				vowel.erase(vowel.begin() + k);
				++guessedVowels;
				++tot;
			}
			else{

				cerr<<"Something went wrong\n";
				exit(0);
			}

		}
		else{
			if(consonant.empty()){

				cerr<<vowel<<endl;
				cerr<<s<<endl;
				cerr<<guess<<endl;
				cerr<<tot<<endl;
				exit(0);
			}
			assert(!consonant.empty());
			int k = guessCharacter(consonant);
			for(int i = 0 ; i < n ; ++i){

				if(consonant[k]==s[i]){

					guess[i] = s[i];
				}
			}
			consonant.erase(consonant.begin() + k);
			++tot;
		}
	}
	assert(tot <= 26);
	return tot;
}

int averageGuessRequired(const string s){

	int n = s.size();
	int tot = 0;
	for(int i = 0 ; i < 3*n ; ++i){

		tot += guessRequired(s);
	}

	tot = tot/(3*n);

	return tot;
}

int main(){

	freopen("words.txt" ,"r" , stdin);
	freopen("dict.txt" , "w" , stdout);

	string s;
	while(cin>>s){

		int tot = averageGuessRequired(s);

		cout<<s<<","<<tot<<endl;
	}
}