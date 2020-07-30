#include <iostream>
#include <fstream>
#include <set>
using namespace std;

int main(){

	freopen("newWords.txt" , "w" , stdout);
	freopen("words.txt" , "r" , stdin);
	
	string s;
	cin>>s;
	while(cin>>s){

		bool ok = true;
		for(char c : s){

			if(c < 'a' || c > 'z'){
				ok = false;
				break;
			}
		}

		if(ok)
			cout<<s<<endl;
	}
}