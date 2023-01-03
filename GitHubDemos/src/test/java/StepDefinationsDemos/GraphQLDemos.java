package StepDefinationsDemos;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
public class GraphQLDemos {

	public static void main(String[] args) {
		//Query		
		String queryResponse=given().log().all().header("Content-Type","application/json")
				.relaxedHTTPSValidation()
				.body("{\"query\":\"query($characterId : Int!,$episodeId :Int!,$ids :[Int!]!){\\n  locationsByIds(ids:$ids){\\n    name\\n    type\\n    dimension\\n  }\\n  \\n  character(characterId : $characterId){\\n    name\\n    type\\n    species\\n    gender\\n    status\\n    origin{\\n      name\\n      type\\n    }\\n    id\\n    episodes{\\n      name\\n      id\\n    }\\n  }\\n  \\n  characters(filters:{name:\\\"Rahul\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      id\\n      type\\n      species\\n      episodes{\\n        episode\\n        air_date\\n        name\\n        created\\n      }\\n    }\\n  }\\n  \\n  episode(episodeId:$episodeId){\\n    air_date\\n    name\\n  }\\n  \\n  locations(filters:{name:\\\"India\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      type\\n      dimension\\n    }\\n  }\\n \\tepisodes(filters:{name:\\\"The Red-Headed League\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      id\\n      air_date\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":92,\"episodeId\":1,\"ids\":[125]}}")
				.when().log().all().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();

		JsonPath path=new JsonPath(queryResponse);
		String state=path.getString("data.locationsByIds.name");
		String name=path.getString("data.character.name");
		System.out.println("state is:\t"+state);
		System.out.println("Name is:\t"+name);
		
		//Mutation
		String mutationResponse=given().log().all().header("Content-Type","application/json")
		.relaxedHTTPSValidation()
		.body("{\"query\":\"mutation{\\n  createLocation(location:{name:\\\"maharashtra\\\",type:\\\"Marathi\\\",dimension:\\\"789\\\"}){\\n    id\\n  }\\n\\tcreateCharacter(character:{\\n    \\tname: \\\"Ganguly\\\",\\n      type:\\\"Diski_scott\\\",\\n      status: \\\"alive\\\",\\n      species: \\\"Human\\\",\\n      gender:\\\"Male\\\",\\n      image:\\\"png\\\",\\n      originId:125,\\n      locationId:125\\n    })\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:\\\"Kannada_money_heist\\\",air_date:\\\"2022-19-19\\\",episode:\\\"123\\\"})\\n  {\\n    id\\n  }\\n  \\n}\",\"variables\":null}")
		.when().log().all().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().body().asPrettyString();
		
		JsonPath json=new JsonPath(mutationResponse);
		String location=json.getString("data.createLocation.id");
		String Char=json.getString("data.createCharacter.id");
		String episode=json.getString("data.createEpisode.id");
		
		System.out.println("Location :\t"+location);
		System.out.println("Char :\t"+Char);
		System.out.println("Episode:\t"+episode);
	}

}
